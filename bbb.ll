declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@TAPE_LEN= dso_local global i32 65536
@BUFFER_LEN= dso_local global i32 32768
@tape = dso_local global [65536 x i32]zeroinitializer
@program = dso_local global [32768 x i32]zeroinitializer
@ptr= dso_local global i32 0
define dso_local i32 @main(){
%x1=alloca i32
store i32 0,i32* %x1
%x2=alloca i32
%x3= call i32 @getint()
store i32 %x3,i32* %x2
br label %x4
x4:
%x5 = load i32, i32* %x1
%x6 = load i32, i32* %x2
%x7 = icmp slt i32 %x5,%x6
%x8 = zext i1 %x7 to i32
%x9= icmp ne i32 %x8, 0
br i1 %x9, label %x10, label %x11
x10:
%x12 = load i32, i32* %x1
%x13 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x14=getelementptr i32,i32* %x13, i32 %x12
%x15= call i32 @getch()
store i32 %x15,i32* %x14
%x16 = load i32, i32* %x1
%x17 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x18=getelementptr i32,i32* %x17, i32 %x16
%x19=load i32,i32* %x18
call void @putint(i32 %x19)
%x20 = load i32, i32* %x1
%x21 = add i32 %x20,1
store i32 %x21,i32* %x1
br label %x4
x11:
%x22 = load i32, i32* %x1
%x23 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x24=getelementptr i32,i32* %x23, i32 %x22
store i32 0,i32* %x24
%x25=alloca i32
%x26=alloca i32
store i32 0,i32* %x1
br label %x27
x27:
%x28 = load i32, i32* %x1
%x29 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x30=getelementptr i32,i32* %x29, i32 %x28
%x31=load i32,i32* %x30
%x32= icmp ne i32 %x31, 0
br i1 %x32, label %x33, label %x34
x33:
%x35 = load i32, i32* %x1
%x36 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x37=getelementptr i32,i32* %x36, i32 %x35
%x38=load i32,i32* %x37
store i32 %x38,i32* %x25
%x39 = load i32, i32* %x25
%x40 = icmp eq i32 %x39,62
%x41 = zext i1 %x40 to i32
%x42= icmp ne i32 %x41, 0
br i1 %x42, label %x43, label %x44
x43:
%x45 = load i32, i32* @ptr
%x46 = add i32 %x45,1
store i32 %x46,i32* @ptr
br label %x47
x44:
%x48 = load i32, i32* %x25
%x49 = icmp eq i32 %x48,60
%x50 = zext i1 %x49 to i32
%x51= icmp ne i32 %x50, 0
br i1 %x51, label %x52, label %x53
x52:
%x54 = load i32, i32* @ptr
%x55 = sub i32 %x54,1
store i32 %x55,i32* @ptr
br label %x56
x53:
%x57 = load i32, i32* %x25
%x58 = icmp eq i32 %x57,43
%x59 = zext i1 %x58 to i32
%x60= icmp ne i32 %x59, 0
br i1 %x60, label %x61, label %x62
x61:
%x63 = load i32, i32* @ptr
%x64 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x65=getelementptr i32,i32* %x64, i32 %x63
%x66 = load i32, i32* @ptr
%x67 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x68=getelementptr i32,i32* %x67, i32 %x66
%x69=load i32,i32* %x68
%x70 = add i32 %x69,1
store i32 %x70,i32* %x65
br label %x71
x62:
%x72 = load i32, i32* %x25
%x73 = icmp eq i32 %x72,45
%x74 = zext i1 %x73 to i32
%x75= icmp ne i32 %x74, 0
br i1 %x75, label %x76, label %x77
x76:
%x78 = load i32, i32* @ptr
%x79 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x80=getelementptr i32,i32* %x79, i32 %x78
%x81 = load i32, i32* @ptr
%x82 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x83=getelementptr i32,i32* %x82, i32 %x81
%x84=load i32,i32* %x83
%x85 = sub i32 %x84,1
store i32 %x85,i32* %x80
br label %x86
x77:
%x87 = load i32, i32* %x25
%x88 = icmp eq i32 %x87,46
%x89 = zext i1 %x88 to i32
%x90= icmp ne i32 %x89, 0
br i1 %x90, label %x91, label %x92
x91:
%x93 = load i32, i32* @ptr
%x94 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x95=getelementptr i32,i32* %x94, i32 %x93
%x96=load i32,i32* %x95
call void @putch(i32 %x96)
br label %x97
x92:
%x98 = load i32, i32* %x25
%x99 = icmp eq i32 %x98,44
%x100 = zext i1 %x99 to i32
%x101= icmp ne i32 %x100, 0
br i1 %x101, label %x102, label %x103
x102:
%x104 = load i32, i32* @ptr
%x105 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x106=getelementptr i32,i32* %x105, i32 %x104
%x107= call i32 @getch()
store i32 %x107,i32* %x106
br label %x108
x103:
%x109 = load i32, i32* %x25
%x110 = load i32, i32* @ptr
%x111 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x112=getelementptr i32,i32* %x111, i32 %x110
%x113=load i32,i32* %x112
%x114 = icmp eq i32 %x109,93
%x115 = zext i1 %x114 to i32
%x116 = and i32 %x115,%x113
%x117= icmp ne i32 %x116, 0
br i1 %x117, label %x118, label %x119
x118:
store i32 1,i32* %x26
br label %x120
x120:
%x121 = load i32, i32* %x26
%x122 = icmp sgt i32 %x121,0
%x123 = zext i1 %x122 to i32
%x124= icmp ne i32 %x123, 0
br i1 %x124, label %x125, label %x126
x125:
%x127 = load i32, i32* %x1
%x128 = sub i32 %x127,1
store i32 %x128,i32* %x1
%x129 = load i32, i32* %x1
%x130 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x131=getelementptr i32,i32* %x130, i32 %x129
%x132=load i32,i32* %x131
store i32 %x132,i32* %x25
%x133 = load i32, i32* %x25
%x134 = icmp eq i32 %x133,91
%x135 = zext i1 %x134 to i32
%x136= icmp ne i32 %x135, 0
br i1 %x136, label %x137, label %x138
x137:
%x139 = load i32, i32* %x26
%x140 = sub i32 %x139,1
store i32 %x140,i32* %x26
br label %x141
x138:
%x142 = load i32, i32* %x25
%x143 = icmp eq i32 %x142,93
%x144 = zext i1 %x143 to i32
%x145= icmp ne i32 %x144, 0
br i1 %x145, label %x146, label %x147
x146:
%x148 = load i32, i32* %x26
%x149 = add i32 %x148,1
store i32 %x149,i32* %x26
br label %x147
x147:
br label %x141
x141:
br label %x120
x126:
br label %x119
x119:
br label %x108
x108:
br label %x97
x97:
br label %x86
x86:
br label %x71
x71:
br label %x56
x56:
br label %x47
x47:
%x150 = load i32, i32* %x1
%x151 = add i32 %x150,1
store i32 %x151,i32* %x1
br label %x27
x34:
ret i32 0
}
