# proot-distro login ubuntu -- bash -c "apt-get update && apt-get --yes --allow -o Dpkg::Options::='--force-confdef' -o Dpkg::Options::='--force-new' upgrade"
# echo -e "#!/bin/bash\napt-get update\napt-get install nano -y" > ~/myscript.sh
# chmod +x ~/myscript.sh
# proot-distro login ubuntu -- bash -c "/data/data/com.termux/files/home/myscript.sh"
apt update
apt-get --yes --allow -o Dpkg::Options::='--force-confdef' -o Dpkg::Options::='--force-new' upgrade
apt-get install coreutils nano nodejs -y
npm i -g --unsafe-perm node-red
