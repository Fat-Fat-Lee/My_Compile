declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
@ascii_0= dso_local global i32 48
define dso_local i32 @main(){
%x1=alloca i32
store i32 0,i32* %x1
%x2=alloca i32
store i32 48,i32* %x2
br label %x3
x3:
%x4= icmp ne i32 1, 0
br i1 %x4, label %x5, label %x6
x5:
%x7 = load i32, i32* %x2
%x8 = sub i32 %x7,1
store i32 %x8,i32* %x2
%x9 = load i32, i32* %x2
%x10 = load i32, i32* %x2
%x11 = icmp slt i32 %x9,0
%x12 = zext i1 %x11 to i32
%x13 = icmp sgt i32 %x10,9
%x14 = zext i1 %x13 to i32
%x15 = or i32 %x12,%x14
%x16= icmp ne i32 %x15, 0
br i1 %x16, label %x17, label %x18
x17:
br label %x3
x18:
br label %x6
x19:
br label %x3
x6:
ret i32 0
}
