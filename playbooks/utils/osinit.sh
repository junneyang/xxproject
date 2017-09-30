#!/bin/bash

# 内核升级
# http://www.linuxidc.com/Linux/2016-08/134218.htm
# http://elrepo.org/tiki/tiki-index.php
yum update –y
rpm --import https://www.elrepo.org/RPM-GPG-KEY-elrepo.org
rpm -Uvh http://www.elrepo.org/elrepo-release-6-8.el6.elrepo.noarch.rpm
yum --enablerepo=elrepo-kernel install kernel-lt -y
# yum --enablerepo=elrepo-kernel install kernel-ml -y
# http://blog.sina.com.cn/s/blog_7211cb9201019hgd.html
sed -i 's/default=1/default=0/g' /boot/grub/grub.conf
reboot

# Kernel Config
# 文件描述符&线程数设置&最大锁定内存地址空间
echo "
* soft nofile 65536
* hard nofile 131072
* soft nproc 2048
* hard nproc 4096
* soft memlock unlimited
* hard memlock unlimited
" >> /etc/security/limits.conf
# 线程数设置
echo "
# Default limit for number of user's processes to prevent
# accidental fork bombs.
# See rhbz #432903 for reasoning.

*          soft    nproc     2048
root       soft    nproc     unlimited
" > /etc/security/limits.d/90-nproc.conf
# 虚拟地址空间
echo "vm.max_map_count=655360" >> /etc/sysctl.conf
sysctl -p
reboot


# Install ansible
yum install gcc python-devel lrzsz -y
yum install epel-release -y
yum install python-pip -y

mkdir -p ~/.pip ~/.pip_download_cache_dir
echo "[global] 
timeout = 60 
trusted-host = mirrors.aliyun.com 
index-url = http://mirrors.aliyun.com/pypi/simple/ 
download_cache = ~/.pip_download_cache_dir 
" > ~/.pip/pip.conf

pip install --upgrade pip
yum install libffi-devel -y
#pip install ansible==1.9.2
yum install ansible -y
yum install libselinux-python -y

# Utils
sudo yum install curl wget -y
