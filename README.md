# META-IOT2050

This [Isar](https://github.com/ilbers/isar) layer contains recipes,
configuration and other artifacts that are specific to  Debian-based IOT2050
product.

## Build example image

Before building the system, you will need to install docker on build host. For
example under Debian Linux

```shell
sudo apt install docker.io
```

If you want to run docker as non-root user then you need to add your user to the
docker group:

```shell
sudo usermod -aG docker $USER
```

You may need to re-login or issue newgrp.

Then open the menu to select the desired image and options:

```shell
./kas-container menu
```

After the build completed, the final image is under

```text
build/tmp/deploy/images/iot2050/iot2050-image-example-iot2050-debian-iot2050.wic
```

## Booting the image from SD card

Under Linux, insert an unused SD card. Assuming the SD card takes device
/dev/mmcblk0, use dd to copy the image to it. For example:

Install the bmap-tools package and run the following command which is generally faster and safer:

```shell
$ sudo bmaptool copy build/tmp/deploy/images/iot2050/iot2050-image-example-iot2050-debian-iot2050.wic /dev/mmcblk0
```

The example image starts with the IP 192.168.200.1 preconfigured on the first
Ethernet interface, and use DHCP at another. You can use ssh to connect to the
system.

The BSP image does not configure the network. If you want to ssh into the
system, you can use the root terminal via UART to ifconfig the IP address and
use that to ssh in.

NOTE: To login, the default username and password is `root`. And you are
required to change the default password when first login.
