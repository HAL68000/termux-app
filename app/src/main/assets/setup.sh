#!/data/data/com.termux/files/usr/bin/bash
apt-get update
apt-get --yes --allow -o Dpkg::Options::="--force-confdef" -o Dpkg::Options::="--force-new" upgrade
# DEBIAN_FRONTEND=noninteractive apt-get upgrade -yq --allow-downgrades --allow-remove-essential --allow-change-held-packages
apt-get install proot -y
apt-get install proot-distro -y
proot-distro install ubuntu
