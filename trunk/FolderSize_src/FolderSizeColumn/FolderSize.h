

/* this ALWAYS GENERATED file contains the definitions for the interfaces */


 /* File created by MIDL compiler version 7.00.0555 */
/* at Thu Nov 14 11:09:14 2013
 */
/* Compiler settings for FolderSize.idl:
    Oicf, W1, Zp8, env=Win32 (32b run), target_arch=X86 7.00.0555 
    protocol : dce , ms_ext, c_ext, robust
    error checks: allocation ref bounds_check enum stub_data 
    VC __declspec() decoration level: 
         __declspec(uuid()), __declspec(selectany), __declspec(novtable)
         DECLSPEC_UUID(), MIDL_INTERFACE()
*/
/* @@MIDL_FILE_HEADING(  ) */

#pragma warning( disable: 4049 )  /* more than 64k source lines */


/* verify that the <rpcndr.h> version is high enough to compile this file*/
#ifndef __REQUIRED_RPCNDR_H_VERSION__
#define __REQUIRED_RPCNDR_H_VERSION__ 475
#endif

#include "rpc.h"
#include "rpcndr.h"

#ifndef __RPCNDR_H_VERSION__
#error this stub requires an updated version of <rpcndr.h>
#endif // __RPCNDR_H_VERSION__


#ifndef __FolderSize_h__
#define __FolderSize_h__

#if defined(_MSC_VER) && (_MSC_VER >= 1020)
#pragma once
#endif

/* Forward Declarations */ 

#ifndef __FolderSizeObj_FWD_DEFINED__
#define __FolderSizeObj_FWD_DEFINED__

#ifdef __cplusplus
typedef class FolderSizeObj FolderSizeObj;
#else
typedef struct FolderSizeObj FolderSizeObj;
#endif /* __cplusplus */

#endif 	/* __FolderSizeObj_FWD_DEFINED__ */


/* header files for imported files */
#include "oaidl.h"
#include "ocidl.h"

#ifdef __cplusplus
extern "C"{
#endif 



#ifndef __FOLDERSIZELib_LIBRARY_DEFINED__
#define __FOLDERSIZELib_LIBRARY_DEFINED__

/* library FOLDERSIZELib */
/* [helpstring][version][uuid] */ 


EXTERN_C const IID LIBID_FOLDERSIZELib;

EXTERN_C const CLSID CLSID_FolderSizeObj;

#ifdef __cplusplus

class DECLSPEC_UUID("04DAAD08-70EF-450E-834A-DCFAF9B48748")
FolderSizeObj;
#endif
#endif /* __FOLDERSIZELib_LIBRARY_DEFINED__ */

/* Additional Prototypes for ALL interfaces */

/* end of Additional Prototypes */

#ifdef __cplusplus
}
#endif

#endif


