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
store i32 20,i32* %x2
br label %x3
x3:
%x4 = load i32, i32* %x1
%x5 = load i32, i32* %x2
%x6 = icmp slt i32 %x4,%x5
%x7 = zext i1 %x6 to i32
%x8= icmp ne i32 %x7, 0
br i1 %x8, label %x9, label %x10
x9:
%x11 = load i32, i32* %x1
%x12 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x13=getelementptr i32,i32* %x12, i32 %x11
%x14 = load i32, i32* %x1
%x15 = add i32 %x14,65
store i32 %x15,i32* %x13
%x16 = load i32, i32* %x1
%x17 = add i32 %x16,1
store i32 %x17,i32* %x1
br label %x3
x10:
%x18 = load i32, i32* %x1
%x19 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x20=getelementptr i32,i32* %x19, i32 %x18
store i32 0,i32* %x20
%x21=alloca i32
%x22=alloca i32
store i32 0,i32* %x1
br label %x23
x23:
%x24 = load i32, i32* %x1
%x25 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x26=getelementptr i32,i32* %x25, i32 %x24
%x27=load i32,i32* %x26
%x28= icmp ne i32 %x27, 0
br i1 %x28, label %x29, label %x30
x29:
%x31 = load i32, i32* %x1
%x32 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x33=getelementptr i32,i32* %x32, i32 %x31
%x34=load i32,i32* %x33
store i32 %x34,i32* %x21
%x35 = load i32, i32* %x21
%x36 = icmp eq i32 %x35,62
%x37 = zext i1 %x36 to i32
%x38= icmp ne i32 %x37, 0
br i1 %x38, label %x39, label %x40
x39:
%x41 = load i32, i32* @ptr
%x42 = add i32 %x41,1
store i32 %x42,i32* @ptr
br label %x43
x40:
%x44 = load i32, i32* %x21
%x45 = icmp eq i32 %x44,60
%x46 = zext i1 %x45 to i32
%x47= icmp ne i32 %x46, 0
br i1 %x47, label %x48, label %x49
x48:
%x50 = load i32, i32* @ptr
%x51 = sub i32 %x50,1
store i32 %x51,i32* @ptr
br label %x52
x49:
%x53 = load i32, i32* %x21
%x54 = icmp eq i32 %x53,43
%x55 = zext i1 %x54 to i32
%x56= icmp ne i32 %x55, 0
br i1 %x56, label %x57, label %x58
x57:
%x59 = load i32, i32* @ptr
%x60 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x61=getelementptr i32,i32* %x60, i32 %x59
%x62 = load i32, i32* @ptr
%x63 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x64=getelementptr i32,i32* %x63, i32 %x62
%x65=load i32,i32* %x64
%x66 = add i32 %x65,1
store i32 %x66,i32* %x61
br label %x67
x58:
%x68 = load i32, i32* %x21
%x69 = icmp eq i32 %x68,45
%x70 = zext i1 %x69 to i32
%x71= icmp ne i32 %x70, 0
br i1 %x71, label %x72, label %x73
x72:
%x74 = load i32, i32* @ptr
%x75 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x76=getelementptr i32,i32* %x75, i32 %x74
%x77 = load i32, i32* @ptr
%x78 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x79=getelementptr i32,i32* %x78, i32 %x77
%x80=load i32,i32* %x79
%x81 = sub i32 %x80,1
store i32 %x81,i32* %x76
br label %x82
x73:
%x83 = load i32, i32* %x21
%x84 = icmp eq i32 %x83,46
%x85 = zext i1 %x84 to i32
%x86= icmp ne i32 %x85, 0
br i1 %x86, label %x87, label %x88
x87:
%x89 = load i32, i32* @ptr
%x90 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x91=getelementptr i32,i32* %x90, i32 %x89
%x92=load i32,i32* %x91
call void @putch(i32 %x92)
br label %x93
x88:
%x94 = load i32, i32* %x21
%x95 = icmp eq i32 %x94,44
%x96 = zext i1 %x95 to i32
%x97= icmp ne i32 %x96, 0
br i1 %x97, label %x98, label %x99
x98:
%x100 = load i32, i32* @ptr
%x101 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x102=getelementptr i32,i32* %x101, i32 %x100
%x103= call i32 @getch()
store i32 %x103,i32* %x102
br label %x104
x99:
%x105 = load i32, i32* %x21
%x106 = load i32, i32* @ptr
%x107 = getelementptr [65536 x i32], [65536 x i32]* @tape, i32 0, i32 0
%x108=getelementptr i32,i32* %x107, i32 %x106
%x109=load i32,i32* %x108
%x110 = icmp eq i32 %x105,93
%x111 = zext i1 %x110 to i32
%x112 = and i32 %x111,%x109
%x113= icmp ne i32 %x112, 0
br i1 %x113, label %x114, label %x115
x114:
store i32 1,i32* %x22
br label %x116
x116:
%x117 = load i32, i32* %x22
%x118 = icmp sgt i32 %x117,0
%x119 = zext i1 %x118 to i32
%x120= icmp ne i32 %x119, 0
br i1 %x120, label %x121, label %x122
x121:
%x123 = load i32, i32* %x1
%x124 = sub i32 %x123,1
store i32 %x124,i32* %x1
%x125 = load i32, i32* %x1
%x126 = getelementptr [32768 x i32], [32768 x i32]* @program, i32 0, i32 0
%x127=getelementptr i32,i32* %x126, i32 %x125
%x128=load i32,i32* %x127
store i32 %x128,i32* %x21
%x129 = load i32, i32* %x21
%x130 = icmp eq i32 %x129,91
%x131 = zext i1 %x130 to i32
%x132= icmp ne i32 %x131, 0
br i1 %x132, label %x133, label %x134
x133:
%x135 = load i32, i32* %x22
%x136 = sub i32 %x135,1
store i32 %x136,i32* %x22
br label %x137
x134:
%x138 = load i32, i32* %x21
%x139 = icmp eq i32 %x138,93
%x140 = zext i1 %x139 to i32
%x141= icmp ne i32 %x140, 0
br i1 %x141, label %x142, label %x143
x142:
%x144 = load i32, i32* %x22
%x145 = add i32 %x144,1
store i32 %x145,i32* %x22
br label %x143
x143:
br label %x137
x137:
br label %x116
x122:
br label %x115
x115:
br label %x104
x104:
br label %x93
x93:
br label %x82
x82:
br label %x67
x67:
br label %x52
x52:
br label %x43
x43:
%x146 = load i32, i32* %x1
%x147 = add i32 %x146,1
store i32 %x147,i32* %x1
br label %x23
x30:
ret i32 0
}
