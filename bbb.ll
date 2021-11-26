declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
declare void @memset(i32*, i32, i32)
@TAPE_LEN= dso_local global i32 65536
@BUFFER_LEN= dso_local global i32 32768
@ptr= dso_local global i32 0
@tape = dso_local global [500 x i32]zeroinitializer
define dso_local i32 @main(){
%x1=alloca i32
store i32 0,i32* %x1
%x2=alloca i32
store i32 33,i32* %x2
%x3=alloca [300 x i32]
%x4 = getelementptr [300 x i32], [300 x i32]* %x3, i32 0, i32 0
call void @memset(i32* %x4, i32 0, i32 1200)
%x5 = getelementptr [300 x i32], [300 x i32]* %x3, i32 0, i32 0
%x6=getelementptr i32,i32* %x5, i32 0
store i32 10, i32* %x6
%x7=getelementptr i32,i32* %x5, i32 1
store i32 43, i32* %x7
%x8=getelementptr i32,i32* %x5, i32 2
store i32 43, i32* %x8
%x9=getelementptr i32,i32* %x5, i32 3
store i32 43, i32* %x9
%x10=getelementptr i32,i32* %x5, i32 4
store i32 43, i32* %x10
%x11=getelementptr i32,i32* %x5, i32 5
store i32 43, i32* %x11
%x12=getelementptr i32,i32* %x5, i32 6
store i32 43, i32* %x12
%x13=getelementptr i32,i32* %x5, i32 7
store i32 43, i32* %x13
%x14=getelementptr i32,i32* %x5, i32 8
store i32 43, i32* %x14
%x15=getelementptr i32,i32* %x5, i32 9
store i32 91, i32* %x15
%x16=getelementptr i32,i32* %x5, i32 10
store i32 45, i32* %x16
%x17=getelementptr i32,i32* %x5, i32 11
store i32 62, i32* %x17
%x18=getelementptr i32,i32* %x5, i32 12
store i32 43, i32* %x18
%x19=getelementptr i32,i32* %x5, i32 13
store i32 43, i32* %x19
%x20=getelementptr i32,i32* %x5, i32 14
store i32 43, i32* %x20
%x21=getelementptr i32,i32* %x5, i32 15
store i32 43, i32* %x21
%x22=getelementptr i32,i32* %x5, i32 16
store i32 43, i32* %x22
%x23=getelementptr i32,i32* %x5, i32 17
store i32 43, i32* %x23
%x24=getelementptr i32,i32* %x5, i32 18
store i32 43, i32* %x24
%x25=getelementptr i32,i32* %x5, i32 19
store i32 43, i32* %x25
%x26=getelementptr i32,i32* %x5, i32 20
store i32 60, i32* %x26
%x27=getelementptr i32,i32* %x5, i32 21
store i32 93, i32* %x27
%x28=getelementptr i32,i32* %x5, i32 22
store i32 93, i32* %x28
%x29=getelementptr i32,i32* %x5, i32 23
store i32 62, i32* %x29
%x30=getelementptr i32,i32* %x5, i32 24
store i32 43, i32* %x30
%x31=getelementptr i32,i32* %x5, i32 25
store i32 43, i32* %x31
%x32=getelementptr i32,i32* %x5, i32 26
store i32 43, i32* %x32
%x33=getelementptr i32,i32* %x5, i32 27
store i32 43, i32* %x33
%x34=getelementptr i32,i32* %x5, i32 28
store i32 43, i32* %x34
%x35=getelementptr i32,i32* %x5, i32 29
store i32 43, i32* %x35
%x36=getelementptr i32,i32* %x5, i32 30
store i32 43, i32* %x36
%x37=getelementptr i32,i32* %x5, i32 31
store i32 43, i32* %x37
%x38=getelementptr i32,i32* %x5, i32 32
store i32 43, i32* %x38
%x39=getelementptr i32,i32* %x5, i32 33
store i32 46, i32* %x39
%x40=alloca i32
%x41=alloca i32
store i32 0,i32* %x1
br label %x42
x42:
%x43 = load i32, i32* %x1
%x44 = getelementptr [300 x i32], [300 x i32]* %x3, i32 0, i32 0
%x45=getelementptr i32,i32* %x44, i32 %x43
%x46=load i32,i32* %x45
%x47= icmp ne i32 %x46, 0
br i1 %x47, label %x48, label %x49
x48:
%x50 = load i32, i32* %x1
%x51 = getelementptr [300 x i32], [300 x i32]* %x3, i32 0, i32 0
%x52=getelementptr i32,i32* %x51, i32 %x50
%x53=load i32,i32* %x52
store i32 %x53,i32* %x40
%x54 = load i32, i32* %x1
call void @putint(i32 %x54)
call void @putch(i32 32)
%x55 = load i32, i32* %x40
call void @putint(i32 %x55)
call void @putch(i32 32)
%x56 = load i32, i32* @ptr
%x57 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x58=getelementptr i32,i32* %x57, i32 %x56
%x59=load i32,i32* %x58
call void @putint(i32 %x59)
call void @putch(i32 10)
%x60 = load i32, i32* %x40
%x61 = icmp eq i32 %x60,62
%x62 = zext i1 %x61 to i32
%x63= icmp ne i32 %x62, 0
br i1 %x63, label %x64, label %x65
x64:
%x66 = load i32, i32* @ptr
%x67 = add i32 %x66,1
store i32 %x67,i32* @ptr
br label %x68
x65:
%x69 = load i32, i32* %x40
%x70 = icmp eq i32 %x69,60
%x71 = zext i1 %x70 to i32
%x72= icmp ne i32 %x71, 0
br i1 %x72, label %x73, label %x74
x73:
%x75 = load i32, i32* @ptr
%x76 = sub i32 %x75,1
store i32 %x76,i32* @ptr
br label %x77
x74:
%x78 = load i32, i32* %x40
%x79 = icmp eq i32 %x78,43
%x80 = zext i1 %x79 to i32
%x81= icmp ne i32 %x80, 0
br i1 %x81, label %x82, label %x83
x82:
%x84 = load i32, i32* @ptr
%x85 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x86=getelementptr i32,i32* %x85, i32 %x84
%x87 = load i32, i32* @ptr
%x88 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x89=getelementptr i32,i32* %x88, i32 %x87
%x90=load i32,i32* %x89
%x91 = add i32 %x90,1
store i32 %x91,i32* %x86
br label %x92
x83:
%x93 = load i32, i32* %x40
%x94 = icmp eq i32 %x93,45
%x95 = zext i1 %x94 to i32
%x96= icmp ne i32 %x95, 0
br i1 %x96, label %x97, label %x98
x97:
%x99 = load i32, i32* @ptr
%x100 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x101=getelementptr i32,i32* %x100, i32 %x99
%x102 = load i32, i32* @ptr
%x103 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x104=getelementptr i32,i32* %x103, i32 %x102
%x105=load i32,i32* %x104
%x106 = sub i32 %x105,1
store i32 %x106,i32* %x101
br label %x107
x98:
%x108 = load i32, i32* %x40
%x109 = icmp eq i32 %x108,46
%x110 = zext i1 %x109 to i32
%x111= icmp ne i32 %x110, 0
br i1 %x111, label %x112, label %x113
x112:
call void @putch(i32 64)
%x114 = load i32, i32* @ptr
%x115 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x116=getelementptr i32,i32* %x115, i32 %x114
%x117=load i32,i32* %x116
call void @putch(i32 %x117)
call void @putch(i32 10)
br label %x118
x113:
%x119 = load i32, i32* %x40
%x120 = icmp eq i32 %x119,44
%x121 = zext i1 %x120 to i32
%x122= icmp ne i32 %x121, 0
br i1 %x122, label %x123, label %x124
x123:
%x125 = load i32, i32* @ptr
%x126 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x127=getelementptr i32,i32* %x126, i32 %x125
%x128= call i32 @getch()
store i32 %x128,i32* %x127
br label %x129
x124:
%x130 = load i32, i32* %x40
%x131 = load i32, i32* @ptr
%x132 = getelementptr [500 x i32], [500 x i32]* @tape, i32 0, i32 0
%x133=getelementptr i32,i32* %x132, i32 %x131
%x134=load i32,i32* %x133
%x135 = icmp eq i32 %x130,93
%x136 = zext i1 %x135 to i32
%x137= icmp ne i32 %x134, 0
%x138 = zext i1 %x137 to i32
%x139= icmp ne i32 %x136, 0
%x140 = zext i1 %x139 to i32
%x141 = and i32 %x140,%x138
%x142= icmp ne i32 %x141, 0
br i1 %x142, label %x143, label %x144
x143:
store i32 1,i32* %x41
br label %x145
x145:
%x146 = load i32, i32* %x41
%x147 = icmp sgt i32 %x146,0
%x148 = zext i1 %x147 to i32
%x149= icmp ne i32 %x148, 0
br i1 %x149, label %x150, label %x151
x150:
%x152 = load i32, i32* %x1
%x153 = sub i32 %x152,1
store i32 %x153,i32* %x1
%x154 = load i32, i32* %x1
%x155 = getelementptr [300 x i32], [300 x i32]* %x3, i32 0, i32 0
%x156=getelementptr i32,i32* %x155, i32 %x154
%x157=load i32,i32* %x156
store i32 %x157,i32* %x40
%x158 = load i32, i32* %x1
call void @putint(i32 %x158)
call void @putch(i32 33)
%x159 = load i32, i32* %x40
call void @putint(i32 %x159)
call void @putch(i32 10)
%x160 = load i32, i32* %x40
%x161 = icmp eq i32 %x160,91
%x162 = zext i1 %x161 to i32
%x163= icmp ne i32 %x162, 0
br i1 %x163, label %x164, label %x165
x164:
%x166 = load i32, i32* %x41
%x167 = sub i32 %x166,1
store i32 %x167,i32* %x41
br label %x168
x165:
%x169 = load i32, i32* %x40
%x170 = icmp eq i32 %x169,93
%x171 = zext i1 %x170 to i32
%x172= icmp ne i32 %x171, 0
br i1 %x172, label %x173, label %x174
x173:
%x175 = load i32, i32* %x41
%x176 = add i32 %x175,1
store i32 %x176,i32* %x41
br label %x174
x174:
br label %x168
x168:
br label %x145
x151:
br label %x144
x144:
br label %x129
x129:
br label %x118
x118:
br label %x107
x107:
br label %x92
x92:
br label %x77
x77:
br label %x68
x68:
%x177 = load i32, i32* %x1
%x178 = add i32 %x177,1
store i32 %x178,i32* %x1
br label %x42
x49:
ret i32 0
}
