pkg update
pkg upgrade
pkg install nodejs-lts
pkg install ndk-sysroot
export ANDROID_NDK_HOME=/data/data/com.termux/files/usr
export GYP_DEFINES="android_ndk_path=$ANDROID_NDK_HOME"
pkg install binutils -y
export N8N_SECURE_COOKIE=false
npm install -g n8n --build-from-source