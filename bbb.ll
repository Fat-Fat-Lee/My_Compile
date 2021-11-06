declare i32 @getint()
declare i32 @getch()
declare void @putint(i32)
declare void @putch(i32)
define dso_local i32 @main(){
%1=alloca i32
store i32 1,i32* %1
%2=alloca i32
store i32 0,i32* %2
%3=alloca i32
store i32 1,i32* %3
%4=alloca i32
store i32 2,i32* %4
%5=alloca i32
store i32 4,i32* %5
%6=alloca i32
store i32 0,i32* %6
br label %7
7:
%8 = load i32, i32* %1
%9 = load i32, i32* %2
%10 = load i32, i32* %3
%11 = load i32, i32* %5
%12 = load i32, i32* %4
%13 = load i32, i32* %1
%14 = load i32, i32* %1
%15 = load i32, i32* %2
%16 = load i32, i32* %3
%17 = load i32, i32* %4
%18 = load i32, i32* %5
%19 = load i32, i32* %1
%20 = load i32, i32* %2
%21 = load i32, i32* %3
%22 = load i32, i32* %4
%23 = load i32, i32* %1
%24 = load i32, i32* %3
%25 = mul i32 %8,%9
%26 = sdiv i32 %25,%10
%27 = add i32 %11,%12
%28 = icmp eq i32 %26,%27
%29 = zext i1 %28 to i32
%30 = add i32 %14,%15
%31 = mul i32 %13,%30
%32 = add i32 %31,%16
%33 = add i32 %17,%18
%34 = icmp sle i32 %32,%33
%35 = zext i1 %34 to i32
%36 = and i32 %29,%35
%37 = mul i32 %20,%21
%38 = sub i32 %19,%37
%39 = sdiv i32 %23,%24
%40 = sub i32 %22,%39
%41 = icmp eq i32 %38,%40
%42 = zext i1 %41 to i32
%43 = or i32 %36,%42
%44= icmp ne i32 %43, 0
br i1 %44,label %45, label %46
45:
store i32 1,i32* %6
br label %46
46:
%47 = load i32, i32* %6
ret i32 %47
}
