# Copyright (C) 2020-2021 NXP
# Released under the MIT license (see COPYING.MIT for the terms)
# The recipe is licensed under MIT (see COPYING.MIT for the terms)

DESCRIPTION = "Sound Open Firmware with Zephyr"
HOMEPAGE = "https://www.sofproject.org"
SECTION = "kernel"
LICENSE = "BSD-3-Clause"
LIC_FILES_CHKSUM = "file://LICENCE;md5=0f00d99239d922ffd13cabef83b33444"

SRC_URI = "${FSL_MIRROR}/${BPN}-${PV}.tar.gz"
SRC_URI[md5sum] = "bcdda4a0ef4e60c5847ae1ca8b67ff75"
SRC_URI[sha256sum] = "03a765b1f0a4eb48717af7ee23b517f3e3bdb26f128ec9ab6ce15dfb415ec7df"

inherit allarch

do_install() {
    # Install firmware image 
    install -d ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-gcc
    cp -r imx/sof/* ${D}${nonarch_base_libdir}/firmware/imx/sof-zephyr-gcc
}

FILES:${PN} = "${nonarch_base_libdir}/firmware/imx/sof-zephyr-gcc"
