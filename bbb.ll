declare i32 @getint()
declare i32 @getch()
declare i32 @getarray(i32*)
declare void @putint(i32)
declare void @putch(i32)
declare void @putarray(i32, i32*)
declare void @memset(i32*, i32, i32)
@golbal = dso_local global [100 x i32][i32 1,i32 2,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0,i32 0]
@x= dso_local global i32 0
@y= dso_local global i32 0
@z= dso_local global i32 0
define dso_local void @f( i32* %x1 , i32 %x2 ){
%x3 = alloca i32* 
store i32* %x1, i32* * %x3
%x4 = alloca i32 
store i32 %x2, i32* %x4
%x5=alloca [27 x i32]
%x6 = getelementptr [27 x i32],[27 x i32]* %x5, i32 0, i32 0
call void @memset(i32* %x6, i32 0, i32 108)
%x7 = getelementptr [27 x i32], [27 x i32]* %x5, i32 0, i32 0
%x8=getelementptr i32,i32* %x7, i32 0
store i32 1, i32* %x8
%x9=getelementptr i32,i32* %x7, i32 1
store i32 2, i32* %x9
%x10=getelementptr i32,i32* %x7, i32 2
store i32 3, i32* %x10
%x11=getelementptr i32,i32* %x7, i32 3
store i32 4, i32* %x11
%x12=getelementptr i32,i32* %x7, i32 4
store i32 5, i32* %x12
%x13=getelementptr i32,i32* %x7, i32 5
store i32 6, i32* %x13
%x14=getelementptr i32,i32* %x7, i32 6
store i32 7, i32* %x14
%x15=getelementptr i32,i32* %x7, i32 7
store i32 8, i32* %x15
%x16=getelementptr i32,i32* %x7, i32 8
store i32 9, i32* %x16
%x17=getelementptr i32,i32* %x7, i32 9
store i32 1, i32* %x17
%x18=getelementptr i32,i32* %x7, i32 10
store i32 2, i32* %x18
%x19=getelementptr i32,i32* %x7, i32 11
store i32 3, i32* %x19
%x20=getelementptr i32,i32* %x7, i32 12
store i32 4, i32* %x20
%x21=getelementptr i32,i32* %x7, i32 13
store i32 5, i32* %x21
%x22=getelementptr i32,i32* %x7, i32 14
store i32 6, i32* %x22
%x23=getelementptr i32,i32* %x7, i32 15
store i32 7, i32* %x23
%x24=getelementptr i32,i32* %x7, i32 16
store i32 8, i32* %x24
%x25=getelementptr i32,i32* %x7, i32 17
store i32 9, i32* %x25
%x26=getelementptr i32,i32* %x7, i32 18
store i32 1, i32* %x26
%x27=getelementptr i32,i32* %x7, i32 19
store i32 2, i32* %x27
%x28=getelementptr i32,i32* %x7, i32 20
store i32 3, i32* %x28
%x29=getelementptr i32,i32* %x7, i32 21
store i32 4, i32* %x29
%x30=getelementptr i32,i32* %x7, i32 22
store i32 5, i32* %x30
%x31=getelementptr i32,i32* %x7, i32 23
store i32 6, i32* %x31
%x32=getelementptr i32,i32* %x7, i32 24
store i32 7, i32* %x32
%x33=getelementptr i32,i32* %x7, i32 25
store i32 8, i32* %x33
%x34=getelementptr i32,i32* %x7, i32 26
store i32 9, i32* %x34
%x35=alloca [27 x i32]
%x36 = getelementptr [27 x i32],[27 x i32]* %x35, i32 0, i32 0
call void @memset(i32* %x36, i32 0, i32 108)
%x37 = getelementptr [27 x i32], [27 x i32]* %x35, i32 0, i32 0
%x38=getelementptr i32,i32* %x37, i32 0
store i32 1, i32* %x38
%x39=getelementptr i32,i32* %x37, i32 1
store i32 2, i32* %x39
%x40=getelementptr i32,i32* %x37, i32 2
store i32 3, i32* %x40
%x41=getelementptr i32,i32* %x37, i32 3
store i32 4, i32* %x41
%x42=getelementptr i32,i32* %x37, i32 4
store i32 5, i32* %x42
%x43=getelementptr i32,i32* %x37, i32 5
store i32 6, i32* %x43
%x44=getelementptr i32,i32* %x37, i32 6
store i32 7, i32* %x44
%x45=getelementptr i32,i32* %x37, i32 7
store i32 8, i32* %x45
%x46=getelementptr i32,i32* %x37, i32 8
store i32 9, i32* %x46
%x47=getelementptr i32,i32* %x37, i32 9
store i32 1, i32* %x47
%x48=getelementptr i32,i32* %x37, i32 10
store i32 2, i32* %x48
%x49=getelementptr i32,i32* %x37, i32 11
store i32 3, i32* %x49
%x50=getelementptr i32,i32* %x37, i32 12
store i32 4, i32* %x50
%x51=getelementptr i32,i32* %x37, i32 13
store i32 5, i32* %x51
%x52=getelementptr i32,i32* %x37, i32 14
store i32 6, i32* %x52
%x53=getelementptr i32,i32* %x37, i32 15
store i32 7, i32* %x53
%x54=getelementptr i32,i32* %x37, i32 16
store i32 8, i32* %x54
%x55=getelementptr i32,i32* %x37, i32 17
store i32 9, i32* %x55
%x56=getelementptr i32,i32* %x37, i32 18
store i32 1, i32* %x56
%x57=getelementptr i32,i32* %x37, i32 19
store i32 2, i32* %x57
%x58=getelementptr i32,i32* %x37, i32 20
store i32 3, i32* %x58
%x59=getelementptr i32,i32* %x37, i32 21
store i32 4, i32* %x59
%x60=getelementptr i32,i32* %x37, i32 22
store i32 5, i32* %x60
%x61=getelementptr i32,i32* %x37, i32 23
store i32 6, i32* %x61
%x62=getelementptr i32,i32* %x37, i32 24
store i32 7, i32* %x62
%x63=getelementptr i32,i32* %x37, i32 25
store i32 8, i32* %x63
%x64=getelementptr i32,i32* %x37, i32 26
store i32 9, i32* %x64
%x65=alloca [27 x i32]
%x66 = getelementptr [27 x i32],[27 x i32]* %x65, i32 0, i32 0
call void @memset(i32* %x66, i32 0, i32 108)
%x67 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 0
%x68=alloca i32
%x69=alloca i32
%x70=alloca i32
%x71=alloca i32
store i32 0,i32* %x71
%x72=alloca i32
store i32 0,i32* %x72
%x73=alloca i32
store i32 0,i32* %x73
br label %x74
x74:
%x78 = load i32, i32* %x71
%x79 = icmp slt i32 %x78,3
%x80 = zext i1 %x79 to i32
%x81= icmp ne i32 %x80, 0
br i1 %x81, label %x75, label %x77
x75:
store i32 0,i32* %x72
br label %x82
x82:
%x86 = load i32, i32* %x72
%x87 = icmp slt i32 %x86,3
%x88 = zext i1 %x87 to i32
%x89= icmp ne i32 %x88, 0
br i1 %x89, label %x83, label %x85
x83:
store i32 0,i32* %x73
br label %x90
x90:
%x94 = load i32, i32* %x73
%x95 = icmp slt i32 %x94,3
%x96 = zext i1 %x95 to i32
%x97= icmp ne i32 %x96, 0
br i1 %x97, label %x91, label %x93
x91:
%x98 = load i32, i32* %x71
%x99 = load i32, i32* %x72
%x100 = load i32, i32* %x73
%x101 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 0
%x103 = mul i32 %x98, 9
%x104 = mul i32 %x99, 3
%x105 = mul i32 %x100, 1
%x106 = add i32 %x103, 0
%x107 = add i32 %x104, %x106
%x108 = add i32 %x105, %x107
%x102=getelementptr i32,i32* %x101, i32 %x108
%x109 = load i32, i32* %x71
%x110 = load i32, i32* %x72
%x111 = load i32, i32* %x73
%x112 = getelementptr [27 x i32], [27 x i32]* %x5, i32 0, i32 0
%x114 = mul i32 %x109, 9
%x115 = mul i32 %x110, 3
%x116 = mul i32 %x111, 1
%x117 = add i32 %x114, 0
%x118 = add i32 %x115, %x117
%x119 = add i32 %x116, %x118
%x113=getelementptr i32,i32* %x112, i32 %x119
%x120=load i32,i32* %x113
%x121 = load i32, i32* %x71
%x122 = load i32, i32* %x72
%x123 = load i32, i32* %x73
%x124 = getelementptr [27 x i32], [27 x i32]* %x35, i32 0, i32 0
%x126 = mul i32 %x121, 9
%x127 = mul i32 %x122, 3
%x128 = mul i32 %x123, 1
%x129 = add i32 %x126, 0
%x130 = add i32 %x127, %x129
%x131 = add i32 %x128, %x130
%x125=getelementptr i32,i32* %x124, i32 %x131
%x132=load i32,i32* %x125
%x133 = add i32 %x120,%x132
store i32 %x133,i32* %x102
%x134 = load i32, i32* %x73
%x135 = add i32 %x134,1
store i32 %x135,i32* %x73
br label %x90
x93:
%x136 = load i32, i32* %x72
%x137 = add i32 %x136,1
store i32 %x137,i32* %x72
br label %x82
x85:
%x138 = load i32, i32* %x71
%x139 = add i32 %x138,1
store i32 %x139,i32* %x71
br label %x74
x77:
%x141 = mul i32 0, 9
%x142 = mul i32 0, 3
%x143 = add i32 %x141, 0
%x144 = add i32 %x142, %x143
%x140 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x144
call void @putarray(i32 3,i32* %x140)
%x146 = mul i32 0, 9
%x147 = mul i32 1, 3
%x148 = add i32 %x146, 0
%x149 = add i32 %x147, %x148
%x145 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x149
call void @putarray(i32 3,i32* %x145)
%x151 = mul i32 0, 9
%x152 = mul i32 2, 3
%x153 = add i32 %x151, 0
%x154 = add i32 %x152, %x153
%x150 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x154
call void @putarray(i32 3,i32* %x150)
%x156 = mul i32 1, 9
%x157 = mul i32 0, 3
%x158 = add i32 %x156, 0
%x159 = add i32 %x157, %x158
%x155 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x159
call void @putarray(i32 3,i32* %x155)
%x161 = mul i32 1, 9
%x162 = mul i32 1, 3
%x163 = add i32 %x161, 0
%x164 = add i32 %x162, %x163
%x160 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x164
call void @putarray(i32 3,i32* %x160)
%x166 = mul i32 1, 9
%x167 = mul i32 2, 3
%x168 = add i32 %x166, 0
%x169 = add i32 %x167, %x168
%x165 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x169
call void @putarray(i32 3,i32* %x165)
%x171 = mul i32 2, 9
%x172 = mul i32 0, 3
%x173 = add i32 %x171, 0
%x174 = add i32 %x172, %x173
%x170 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x174
call void @putarray(i32 3,i32* %x170)
%x176 = mul i32 2, 9
%x177 = mul i32 1, 3
%x178 = add i32 %x176, 0
%x179 = add i32 %x177, %x178
%x175 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x179
call void @putarray(i32 3,i32* %x175)
%x181 = mul i32 2, 9
%x182 = mul i32 2, 3
%x183 = add i32 %x181, 0
%x184 = add i32 %x182, %x183
%x180 = getelementptr [27 x i32], [27 x i32]* %x65, i32 0, i32 %x184
call void @putarray(i32 3,i32* %x180)
ret void
}
define dso_local i32 @main(){
%x185 = getelementptr [100 x i32], [100 x i32]* @golbal, i32 0, i32 0
call void @f(i32* %x185,i32 16)
ret i32 0
ret i32 0
}
