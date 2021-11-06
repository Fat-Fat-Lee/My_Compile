declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
store i32 10,i32* %1
%2=alloca i32
store i32 5,i32* %2
%3=alloca i32
store i32 3,i32* %3
%4=alloca i32
store i32 0,i32* %4
br label %5
5:
%6 = load i32, i32* %3
%7 = load i32, i32* %1
%8 = load i32, i32* %2
%9 = icmp slt i32 %6,%7
%10 = zext i1 %9 to i32
%11 = icmp slt i32 %10,%8
%12 = zext i1 %11 to i32
%13= icmp ne i32 %12, 0
br i1 %13,label %14, label %38
14:
store i32 1,i32* %4
br label %15
15:
%16 = load i32, i32* %2
%17 = icmp sle i32 %16,5
%18 = zext i1 %17 to i32
%19= icmp ne i32 %18, 0
br i1 %19,label %20, label %35
20:
store i32 2,i32* %4
br label %21
21:
%22 = load i32, i32* %3
%23 = icmp slt i32 %22,3
%24 = zext i1 %23 to i32
%25= icmp ne i32 %24, 0
br i1 %25,label %26, label %27
26:
store i32 3,i32* %4
br label %34

27:
%28 = load i32, i32* %3
%29 = icmp eq i32 %28,3
%30 = zext i1 %29 to i32
%31= icmp ne i32 %30, 0
br i1 %31,label %32, label %33
32:
store i32 5,i32* %4
br label %34
33:
store i32 6,i32* %4
br label %34
34:
store i32 7,i32* %4
br label %36
35:
store i32 9,i32* %4
br label %36
36:
%37 = load i32, i32* %4
ret i32 %37
br label %40
38:
store i32 11,i32* %4
%39 = load i32, i32* %4
ret i32 %39
br label %40
40:
%41 = load i32, i32* %4
ret i32 %41
}
