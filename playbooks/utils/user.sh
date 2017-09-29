#!/bin/bash
# set -eux

export PROJECT_USER=xxproject
export PROJECT_GROUP=xxproject

userdel ${PROJECT_USER}
groupdel ${PROJECT_GROUP}
groupadd ${PROJECT_GROUP}
useradd ${PROJECT_USER} -g ${PROJECT_GROUP}

mkdir /home/${PROJECT_USER}/.ssh
ssh-keygen -t rsa -P '' -f /home/${PROJECT_USER}/.ssh/id_rsa
touch /home/${PROJECT_USER}/.ssh/authorized_keys
cat /home/${PROJECT_USER}/.ssh/id_rsa.pub > /home/${PROJECT_USER}/.ssh/authorized_keys
chown -R ${PROJECT_USER}:${PROJECT_GROUP} /home/${PROJECT_USER}/.ssh
chmod 600 /home/${PROJECT_USER}/.ssh/authorized_keys

echo "auth required pam_succeed_if.so user != root quiet" >> /etc/pam.d/login
echo "PermitRootLogin no" >> /etc/ssh/sshd_config
echo "${PROJECT_USER}    ALL=(ALL)    NOPASSWD:    ALL" >> /etc/sudoers
service sshd restart

