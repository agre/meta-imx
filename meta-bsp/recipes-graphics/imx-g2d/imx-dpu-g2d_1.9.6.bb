# Copyright (C) 2016 Freescale Semiconductor
# Copyright 2017-2021 NXP
# Released under the MIT license (see COPYING.MIT for the terms)

DESCRIPTION = "GPU G2D library and apps for i.MX with 2D GPU and DPU"
LICENSE = "Proprietary"
LIC_FILES_CHKSUM = "file://COPYING;md5=e565271ec9a80ce47abbddc4bffe56fa"
PROVIDES += "virtual/libg2d"

SRC_URI = "${FSL_MIRROR}/${BPN}-${PV}.bin;fsl-eula=true"
SRC_URI[md5sum] = "be7371e91daa559ce873428e8baefb2e"
SRC_URI[sha256sum] = "5443969d1ca53203c326be1088888640f6381d5f2ad6e79006ee06e7cab25249"

inherit fsl-eula-unpack

do_install () {
    install -d ${D}${libdir}
    install -d ${D}${includedir}
    cp -r --no-preserve=ownership ${S}/g2d/usr/lib/*.so* ${D}${libdir}
    cp -r --no-preserve=ownership ${S}/g2d/usr/include/* ${D}${includedir}
    cp -r -d --no-preserve=ownership ${S}/gpu-demos/opt ${D}
}

FILES:${PN} += "/opt"
INSANE_SKIP:${PN} += "ldflags"

RDEPENDS:${PN} = "libgal-imx libdrm"

# This is required to provide support for VPU Amphion HEVC tile format
# From NXP [MGS-5547] (commit e175d6b4f78deab24d319b852998bef55cdecc99):
# VPU Amphion HEVC tile support was added using OpenCL, so add a dependency on libopencl-imx.
RDEPENDS:${PN} += "libopencl-imx"

COMPATIBLE_MACHINE = "(imxdpu)"
