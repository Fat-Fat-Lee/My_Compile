declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@__HELLO = dso_local global [100 x i32][i32 87,i32 101,i32 108,i32 99,i32 111,i32 109,i32 101,i32 32,i32 116,i32 111,i32 32,i32 116,i32 104,i32 101,i32 32,i32 74,i32 97,i32 112,i32 97,i32 114,i32 105,i32 32,i32 80,i32 97,i32 114,i32 107,i32 33,i32 10,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]
@N4__mE___ = dso_local global [6x[50 x i32]][[50 x i32][i32 83,i32 97,i32 97,i32 98,i32 97,i32 114,i32 117,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 75,i32 97,i32 98,i32 97,i32 110,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 72,i32 97,i32 115,i32 104,i32 105,i32 98,i32 105,i32 114,i32 111,i32 107,i32 111,i32 117,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 65,i32 114,i32 97,i32 105,i32 103,i32 117,i32 109,i32 97,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 72,i32 117,i32 110,i32 98,i32 111,i32 114,i32 117,i32 116,i32 111,i32 32,i32 80,i32 101,i32 110,i32 103,i32 105,i32 110,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 84,i32 97,i32 105,i32 114,i32 105,i32 107,i32 117,i32 32,i32 79,i32 111,i32 107,i32 97,i32 109,i32 105,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]]
@saY_HeI10_To = dso_local global [40 x i32][i32 32,i32 115,i32 97,i32 121,i32 115,i32 32,i32 104,i32 101,i32 108,i32 108,i32 111,i32 32,i32 116,i32 111,i32 32,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]
@RET = dso_local global [5 x i32][i32 10,i32 0,i32 0,i32 0,i32 0]
define dso_local i32 @main(){
%x1=alloca i32
store i32 0,i32* %x1
br label %x2
x2:
%x3 = load i32, i32* %x1
%x4 = getelementptr [100 x i32], [100 x i32]* @__HELLO, i32 0, i32 0
%x5=getelementptr i32,i32* %x4, i32 %x3
%x6=load i32,i32* %x5
%x7= icmp ne i32 %x6, 0
br i1 %x7, label %x8, label %x9
x8:
%x10 = load i32, i32* %x1
%x11 = getelementptr [100 x i32], [100 x i32]* @__HELLO, i32 0, i32 0
%x12=getelementptr i32,i32* %x11, i32 %x10
%x13=load i32,i32* %x12
call void @putch(i32 %x13)
%x14 = load i32, i32* %x1
%x15 = add i32 %x14,1
store i32 %x15,i32* %x1
br label %x2
x9:
%x16=alloca i32
store i32 0,i32* %x16
br label %x17
x17:
%x18= icmp ne i32 1, 0
br i1 %x18, label %x19, label %x20
x19:
%x21=alloca i32
%x22 = load i32, i32* %x16
%x23 = sdiv i32 %x22,6
store i32 %x23,i32* %x21
%x24=alloca i32
%x25 = load i32, i32* %x16
%x26 = srem i32 %x25,6
store i32 %x26,i32* %x24
%x27 = load i32, i32* %x21
%x28 = load i32, i32* %x24
%x29 = icmp ne i32 %x27,%x28
%x30 = zext i1 %x29 to i32
%x31= icmp ne i32 %x30, 0
br i1 %x31, label %x32, label %x33
x32:
store i32 0,i32* %x1
br label %x34
x34:
%x35 = load i32, i32* %x21
%x36 = load i32, i32* %x1
%x37 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x38 = getelementptr [50 x i32], [50 x i32]* %x37, i32 0, i32 0
%x40=mul i32 %x35,50
%x41=add i32 %x40,%x36
%x39=getelementptr i32,i32* %x38, i32 %x41
%x42=load i32,i32* %x39
%x43= icmp ne i32 %x42, 0
br i1 %x43, label %x44, label %x45
x44:
%x46 = load i32, i32* %x21
%x47 = load i32, i32* %x1
%x48 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x49 = getelementptr [50 x i32], [50 x i32]* %x48, i32 0, i32 0
%x51=mul i32 %x46,50
%x52=add i32 %x51,%x47
%x50=getelementptr i32,i32* %x49, i32 %x52
%x53=load i32,i32* %x50
call void @putch(i32 %x53)
%x54 = load i32, i32* %x1
%x55 = add i32 %x54,1
store i32 %x55,i32* %x1
br label %x34
x45:
store i32 0,i32* %x1
br label %x56
x56:
%x57 = load i32, i32* %x1
%x58 = getelementptr [40 x i32], [40 x i32]* @saY_HeI10_To, i32 0, i32 0
%x59=getelementptr i32,i32* %x58, i32 %x57
%x60=load i32,i32* %x59
%x61= icmp ne i32 %x60, 0
br i1 %x61, label %x62, label %x63
x62:
%x64 = load i32, i32* %x1
%x65 = getelementptr [40 x i32], [40 x i32]* @saY_HeI10_To, i32 0, i32 0
%x66=getelementptr i32,i32* %x65, i32 %x64
%x67=load i32,i32* %x66
call void @putch(i32 %x67)
%x68 = load i32, i32* %x1
%x69 = add i32 %x68,1
store i32 %x69,i32* %x1
br label %x56
x63:
store i32 0,i32* %x1
br label %x70
x70:
%x71 = load i32, i32* %x24
%x72 = load i32, i32* %x1
%x73 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x74 = getelementptr [50 x i32], [50 x i32]* %x73, i32 0, i32 0
%x76=mul i32 %x71,50
%x77=add i32 %x76,%x72
%x75=getelementptr i32,i32* %x74, i32 %x77
%x78=load i32,i32* %x75
%x79= icmp ne i32 %x78, 0
br i1 %x79, label %x80, label %x81
x80:
%x82 = load i32, i32* %x24
%x83 = load i32, i32* %x1
%x84 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x85 = getelementptr [50 x i32], [50 x i32]* %x84, i32 0, i32 0
%x87=mul i32 %x82,50
%x88=add i32 %x87,%x83
%x86=getelementptr i32,i32* %x85, i32 %x88
%x89=load i32,i32* %x86
call void @putch(i32 %x89)
%x90 = load i32, i32* %x1
%x91 = add i32 %x90,1
store i32 %x91,i32* %x1
br label %x70
x81:
store i32 0,i32* %x1
br label %x92
x92:
%x93 = load i32, i32* %x1
%x94 = getelementptr [5 x i32], [5 x i32]* @RET, i32 0, i32 0
%x95=getelementptr i32,i32* %x94, i32 %x93
%x96=load i32,i32* %x95
%x97= icmp ne i32 %x96, 0
br i1 %x97, label %x98, label %x99
x98:
%x100 = load i32, i32* %x1
%x101 = getelementptr [5 x i32], [5 x i32]* @RET, i32 0, i32 0
%x102=getelementptr i32,i32* %x101, i32 %x100
%x103=load i32,i32* %x102
call void @putch(i32 %x103)
%x104 = load i32, i32* %x1
%x105 = add i32 %x104,1
store i32 %x105,i32* %x1
br label %x92
x99:
br label %x33
x33:
%x106 = load i32, i32* %x16
%x107 = mul i32 %x106,17
%x108 = add i32 %x107,23
%x109 = srem i32 %x108,32
store i32 %x109,i32* %x16
%x110 = load i32, i32* %x16
%x111 = icmp eq i32 %x110,0
%x112 = zext i1 %x111 to i32
%x113= icmp ne i32 %x112, 0
br i1 %x113, label %x114, label %x115
x114:
br label %x99
x115:
br label %x17
x20:
ret i32 0
}
