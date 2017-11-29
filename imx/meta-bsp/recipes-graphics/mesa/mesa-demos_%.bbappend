FILESEXTRAPATHS_prepend := "${THISDIR}/${PN}:"

DEPENDS = "mesa"

SRC_URI_append_imxgpu = " file://Replace-glWindowPos2iARB-calls-with-glWindowPos2i.patch \
                    file://fix-clear-build-break.patch \
                    file://Additional-eglSwapBuffer-calling-makes-wrong-throttl.patch \
                    file://Add-OpenVG-demos-to-support-wayland.patch"

PACKAGECONFIG_IMX_TO_REMOVE = ""
PACKAGECONFIG_IMX_TO_REMOVE_imxgpu2d = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'glu x11', '', d)} \
    gles1 \
    gles2 \
"
PACKAGECONFIG_IMX_TO_REMOVE_imxgpu3d = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'glu x11', '', d)} \
"

PACKAGECONFIG_IMX_TO_APPEND = ""
PACKAGECONFIG_IMX_TO_APPEND_imxgpu2d = " \
    ${@bb.utils.contains('DISTRO_FEATURES', 'wayland', 'wayland vg', '', d)} \
    ${@bb.utils.contains('DISTRO_FEATURES', 'x11', 'glut', '', d)} \
"

PACKAGECONFIG_remove = " ${PACKAGECONFIG_IMX_TO_REMOVE}"
PACKAGECONFIG_append = " ${PACKAGECONFIG_IMX_TO_APPEND}"

PACKAGECONFIG[glut] = "--with-glut=${STAGING_EXECPREFIXDIR},--without-glut,freeglut"
