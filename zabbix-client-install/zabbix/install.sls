include:
  - init.install

zabbix-server-install:
  file.managed:
    - name: /usr/local/src/zabbix-3.4.9.tar.gz
    - source: salt://zabbix/files/zabbix-3.4.9.tar.gz
    - user: root
    - gourp: root
    - mode: 644
  cmd.run:
    - name: cd /usr/local/src && tar  zxf zabbix-3.4.9.tar.gz && cd zabbix-3.4.9 && ./configure   --prefix=/usr/local/zabbix  --enable-agent --sysconfdir=/etc/zabbix && make && make install
    - unless: test -d /usr/local/zabbix
    - require:
      - pkg: init-pkh-install

zabbix_init:
  file.managed:
    - name: /etc/init.d/zabbix_agentd
    - source: salt://zabbix/files/zabbix_agentd
    - user: root
    - group: root
    - mode: 755
  cmd.run:
    - name: chkconfig --add zabbix_agentd
    - unless: chkconfig --list |grep zabbix_agentd
    - require:
      - file: zabbix-server-install
zabbix:
  user.present:
    - shell: /sbin/nologin
    - home: /home/zabbix
    - uid: 777
/var/log/zabbix:
  file.directory:
    - user: zabbix
    - group: zabbix
    - mode: 755
    - makedirs: True

zabbix_files:
  file.managed:
    - name: /etc/zabbix/zabbix_agentd.conf
    - source: salt://zabbix/files/zabbix_agentd.conf
    - user: root
    - group: root
    - mode: 0755


zabbix_file:
  file.directory:
    - name: /etc/zabbix/
    - require:
      - cmd: zabbix-server-install
  service.running:
    - name: zabbix_agentd
    - enable: True
    - reload: True
    - require:
      - cmd: zabbix_init
    - watch:
      - file: /etc/zabbix/zabbix_agentd.conf
