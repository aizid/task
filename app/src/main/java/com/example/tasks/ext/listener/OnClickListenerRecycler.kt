package com.example.tasks.ext.listener

interface OnClickStr { fun onClickStr(value: String?) }
interface OnClickInt { fun onClickInt(value: Int?) }
interface OnClickStrInt { fun onClickStrInt(valueStr: String?, valueInt: Int?) }
interface OnClickInts { fun onClickInts(valueInt: Int?, valueInts: Int?) }
interface OnClickStrs { fun onClickStrs(valueStr: String?, valueStrs: String?) }
interface OnClickAny { fun onClickAny(valueAny: Any?) }
