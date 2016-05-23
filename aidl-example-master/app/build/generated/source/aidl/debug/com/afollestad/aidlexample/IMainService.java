/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\1jason\\3code\\git_code\\android_code\\aidl-example-master\\app\\src\\main\\aidl\\com\\afollestad\\aidlexample\\IMainService.aidl
 */
package com.afollestad.aidlexample;
public interface IMainService extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.afollestad.aidlexample.IMainService
{
private static final java.lang.String DESCRIPTOR = "com.afollestad.aidlexample.IMainService";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.afollestad.aidlexample.IMainService interface,
 * generating a proxy if needed.
 */
public static com.afollestad.aidlexample.IMainService asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.afollestad.aidlexample.IMainService))) {
return ((com.afollestad.aidlexample.IMainService)iin);
}
return new com.afollestad.aidlexample.IMainService.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_listFiles:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
com.afollestad.aidlexample.MainObject[] _result = this.listFiles(_arg0);
reply.writeNoException();
reply.writeTypedArray(_result, android.os.Parcelable.PARCELABLE_WRITE_RETURN_VALUE);
return true;
}
case TRANSACTION_exit:
{
data.enforceInterface(DESCRIPTOR);
this.exit();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.afollestad.aidlexample.IMainService
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
@Override public com.afollestad.aidlexample.MainObject[] listFiles(java.lang.String path) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
com.afollestad.aidlexample.MainObject[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(path);
mRemote.transact(Stub.TRANSACTION_listFiles, _data, _reply, 0);
_reply.readException();
_result = _reply.createTypedArray(com.afollestad.aidlexample.MainObject.CREATOR);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void exit() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_exit, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_listFiles = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_exit = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
}
public com.afollestad.aidlexample.MainObject[] listFiles(java.lang.String path) throws android.os.RemoteException;
public void exit() throws android.os.RemoteException;
}
