declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@__HELLO = dso_local global [100 x i32][i32 87,i32 101,i32 108,i32 99,i32 111,i32 109,i32 101,i32 32,i32 116,i32 111,i32 32,i32 116,i32 104,i32 101,i32 32,i32 74,i32 97,i32 112,i32 97,i32 114,i32 105,i32 32,i32 80,i32 97,i32 114,i32 107,i32 33,i32 10,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]
@N4__mE___ = dso_local global [6 x [50 x i32]][[50 x i32][i32 83,i32 97,i32 97,i32 98,i32 97,i32 114,i32 117,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 75,i32 97,i32 98,i32 97,i32 110,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 72,i32 97,i32 115,i32 104,i32 105,i32 98,i32 105,i32 114,i32 111,i32 107,i32 111,i32 117,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 65,i32 114,i32 97,i32 105,i32 103,i32 117,i32 109,i32 97,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 72,i32 117,i32 110,i32 98,i32 111,i32 114,i32 117,i32 116,i32 111,i32 32,i32 80,i32 101,i32 110,i32 103,i32 105,i32 110,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0],[50 x i32][i32 84,i32 97,i32 105,i32 114,i32 105,i32 107,i32 117,i32 32,i32 79,i32 111,i32 107,i32 97,i32 109,i32 105,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]]
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
%x38 = getelementptr [50 x i32], [50 x i32]* %x37, i32 %x35, i32 %x36
%x39=load i32,i32* %x38
%x40= icmp ne i32 %x39, 0
br i1 %x40, label %x41, label %x42
x41:
%x43 = load i32, i32* %x21
%x44 = load i32, i32* %x1
%x45 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x46 = getelementptr [50 x i32], [50 x i32]* %x45, i32 %x43, i32 %x44
%x47=load i32,i32* %x46
call void @putch(i32 %x47)
%x48 = load i32, i32* %x1
%x49 = add i32 %x48,1
store i32 %x49,i32* %x1
br label %x34
x42:
store i32 0,i32* %x1
br label %x50
x50:
%x51 = load i32, i32* %x1
%x52 = getelementptr [40 x i32], [40 x i32]* @saY_HeI10_To, i32 0, i32 0
%x53=getelementptr i32,i32* %x52, i32 %x51
%x54=load i32,i32* %x53
%x55= icmp ne i32 %x54, 0
br i1 %x55, label %x56, label %x57
x56:
%x58 = load i32, i32* %x1
%x59 = getelementptr [40 x i32], [40 x i32]* @saY_HeI10_To, i32 0, i32 0
%x60=getelementptr i32,i32* %x59, i32 %x58
%x61=load i32,i32* %x60
call void @putch(i32 %x61)
%x62 = load i32, i32* %x1
%x63 = add i32 %x62,1
store i32 %x63,i32* %x1
br label %x50
x57:
store i32 0,i32* %x1
br label %x64
x64:
%x65 = load i32, i32* %x24
%x66 = load i32, i32* %x1
%x67 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x68 = getelementptr [50 x i32], [50 x i32]* %x67, i32 %x65, i32 %x66
%x69=load i32,i32* %x68
%x70= icmp ne i32 %x69, 0
br i1 %x70, label %x71, label %x72
x71:
%x73 = load i32, i32* %x24
%x74 = load i32, i32* %x1
%x75 = getelementptr [6 x [50 x i32]],[6 x [50 x i32]]* @N4__mE___, i32 0, i32 0
%x76 = getelementptr [50 x i32], [50 x i32]* %x75, i32 %x73, i32 %x74
%x77=load i32,i32* %x76
call void @putch(i32 %x77)
%x78 = load i32, i32* %x1
%x79 = add i32 %x78,1
store i32 %x79,i32* %x1
br label %x64
x72:
store i32 0,i32* %x1
br label %x80
x80:
%x81 = load i32, i32* %x1
%x82 = getelementptr [5 x i32], [5 x i32]* @RET, i32 0, i32 0
%x83=getelementptr i32,i32* %x82, i32 %x81
%x84=load i32,i32* %x83
%x85= icmp ne i32 %x84, 0
br i1 %x85, label %x86, label %x87
x86:
%x88 = load i32, i32* %x1
%x89 = getelementptr [5 x i32], [5 x i32]* @RET, i32 0, i32 0
%x90=getelementptr i32,i32* %x89, i32 %x88
%x91=load i32,i32* %x90
call void @putch(i32 %x91)
%x92 = load i32, i32* %x1
%x93 = add i32 %x92,1
store i32 %x93,i32* %x1
br label %x80
x87:
br label %x33
x33:
%x94 = load i32, i32* %x16
%x95 = mul i32 %x94,17
%x96 = add i32 %x95,23
%x97 = srem i32 %x96,32
store i32 %x97,i32* %x16
%x98 = load i32, i32* %x16
%x99 = icmp eq i32 %x98,0
%x100 = zext i1 %x99 to i32
%x101= icmp ne i32 %x100, 0
br i1 %x101, label %x102, label %x103
x102:
br label %x20
x103:
br label %x17
x20:
ret i32 0
}
