#
# Copyright (c) Siemens AG, 2020
#
# Authors:
#  Jan Kiszka <jan.kiszka@siemens.com>
#
# This file is subject to the terms and conditions of the MIT License.  See
# COPYING.MIT file in the top-level directory.
#

require recipes-bsp/optee-os/optee-os-custom.inc

SRC_URI += "https://github.com/OP-TEE/optee_os/archive/${PV}.tar.gz"
SRC_URI[sha256sum] = "bdd309697745ec4406951652094b50d9adb06c3612f01bd8a3d72682ec8e03e8"

S = "${WORKDIR}/optee_os-${PV}"

DEBIAN_BUILD_DEPENDS += ", python3-cryptography:native"

OPTEE_NAME = "iot2050"

OPTEE_PLATFORM = "k3-am65x"
OPTEE_EXTRA_BUILDARGS = " \
    CFG_ARM64_core=y CFG_TEE_CORE_LOG_LEVEL=2 CFG_USER_TA_TARGETS=ta_arm64 \
    CFG_CONSOLE_UART=1 CFG_RPMB_FS=y CFG_RPMB_FS_DEV_ID=1 CFG_CORE_DYN_SHM=y \
    CFG_IN_TREE_EARLY_TAS=avb/023f8f1a-292a-432b-8fc4-de8471358067"

OPTEE_EXTRA_BUILDARGS_append_rpmb-setup = " CFG_RPMB_WRITE_KEY=y"

dpkg_runbuild_prepend() {
    export TEE_IMPL_VERSION=${PV}
}
