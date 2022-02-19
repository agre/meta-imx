SUMMARY = "Userspace interface to the kernel DRM services"
DESCRIPTION = "The runtime library for accessing the kernel DRM services.  DRM \
stands for \"Direct Rendering Manager\", which is the kernel portion of the \
\"Direct Rendering Infrastructure\" (DRI).  DRI is required for many hardware \
accelerated OpenGL drivers."
HOMEPAGE = "http://dri.freedesktop.org"
SECTION = "x11/base"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://xf86drm.c;beginline=9;endline=32;md5=c8a3b961af7667c530816761e949dc71"
PROVIDES = "drm"
DEPENDS = "libpthread-stubs"

IMX_LIBDRM_SRC ?= "git://source.codeaurora.org/external/imx/libdrm-imx.git;protocol=https;nobranch=1"
IMX_LIBDRM_BRANCH ?= "libdrm-imx-2.4.109"
SRC_URI = "${IMX_LIBDRM_SRC};branch=${IMX_LIBDRM_BRANCH} \
           file://0001-meson-add-libdrm-vivante-to-the-meson-meta-data.patch "
SRCREV = "3c682629398fd6ee702be9941758d44299a3280a" 
S = "${WORKDIR}/git"

DEFAULT_PREFERENCE = "-1"
COMPATIBLE_MACHINE = "(mx6|mx7|mx8)"

inherit meson pkgconfig manpages

PACKAGECONFIG ??= "libkms intel radeon amdgpu nouveau vmwgfx omap freedreno vc4 etnaviv install-test-programs"
PACKAGECONFIG[libkms] = "-Dlibkms=true,-Dlibkms=false"
PACKAGECONFIG[intel] = "-Dintel=true,-Dintel=false,libpciaccess"
PACKAGECONFIG[radeon] = "-Dradeon=true,-Dradeon=false"
PACKAGECONFIG[amdgpu] = "-Damdgpu=true,-Damdgpu=false"
PACKAGECONFIG[nouveau] = "-Dnouveau=true,-Dnouveau=false"
PACKAGECONFIG[vmwgfx] = "-Dvmwgfx=true,-Dvmwgfx=false"
PACKAGECONFIG[omap] = "-Domap=true,-Domap=false"
PACKAGECONFIG[exynos] = "-Dexynos=true,-Dexynos=false"
PACKAGECONFIG[freedreno] = "-Dfreedreno=true,-Dfreedreno=false"
PACKAGECONFIG[tegra] = "-Dtegra=true,-Dtegra=false"
PACKAGECONFIG[vc4] = "-Dvc4=true,-Dvc4=false"
PACKAGECONFIG[etnaviv] = "-Detnaviv=true,-Detnaviv=false"
PACKAGECONFIG[freedreno-kgsl] = "-Dfreedreno-kgsl=true,-Dfreedreno-kgsl=false"
PACKAGECONFIG[valgrind] = "-Dvalgrind=true,-Dvalgrind=false,valgrind"
PACKAGECONFIG[install-test-programs] = "-Dinstall-test-programs=true,-Dinstall-test-programs=false"
PACKAGECONFIG[cairo-tests] = "-Dcairo-tests=true,-Dcairo-tests=false"
PACKAGECONFIG[udev] = "-Dudev=true,-Dudev=false,udev"
PACKAGECONFIG[manpages] = "-Dman-pages=true,-Dman-pages=false,libxslt-native xmlto-native python3-docutils-native"

ALLOW_EMPTY:${PN}-drivers = "1"
PACKAGES =+ "${PN}-tests ${PN}-drivers ${PN}-radeon ${PN}-nouveau ${PN}-omap \
             ${PN}-intel ${PN}-exynos ${PN}-kms ${PN}-freedreno ${PN}-amdgpu \
             ${PN}-etnaviv"

RRECOMMENDS:${PN}-drivers = "${PN}-radeon ${PN}-nouveau ${PN}-omap ${PN}-intel \
                             ${PN}-exynos ${PN}-freedreno ${PN}-amdgpu \
                             ${PN}-etnaviv"

FILES:${PN}-tests = "${bindir}/*"
FILES:${PN}-radeon = "${libdir}/libdrm_radeon.so.*"
FILES:${PN}-nouveau = "${libdir}/libdrm_nouveau.so.*"
FILES:${PN}-omap = "${libdir}/libdrm_omap.so.*"
FILES:${PN}-intel = "${libdir}/libdrm_intel.so.*"
FILES:${PN}-exynos = "${libdir}/libdrm_exynos.so.*"
FILES:${PN}-kms = "${libdir}/libkms*.so.*"
FILES:${PN}-freedreno = "${libdir}/libdrm_freedreno.so.*"
FILES:${PN}-amdgpu = "${libdir}/libdrm_amdgpu.so.* ${datadir}/${PN}/amdgpu.ids"
FILES:${PN}-etnaviv = "${libdir}/libdrm_etnaviv.so.*"

BBCLASSEXTEND = "native nativesdk"

PACKAGES:prepend:imxgpu = "${PN}-vivante "
RRECOMMENDS:${PN}-drivers:append:imxgpu = " ${PN}-vivante"
FILES:${PN}-vivante = "${libdir}/libdrm_vivante.so.*"
PACKAGECONFIG:append:imxgpu = " vivante"
PACKAGECONFIG[vivante] = "-Dvivante=true,-Dvivante=false"

PACKAGE_ARCH = "${MACHINE_SOCARCH}"
