#include<bits/stdc++.h>
using namespace std;
ifstream infile("F:\\����ԭ��\\���Ĵ�ʵ��\\result.txt");//�ļ���
ifstream infile2("F:\\����ԭ��\\���Ĵ�ʵ��\\analysis.txt");//�ļ���
ofstream outfile("F:\\����ԭ��\\���Ĵ�ʵ��\\result.txt");//�ļ������
map<string,string> word;//Ӧ��map���ݽṹ�γ�һ��string->string�Ķ�Ӧ
std::map<string,string>::iterator it;//��������������Ӧ��ϵ�ĵ�����
string str;//������ַ���
string sym;//����ָʾ����ķ���
string sym2;//����ָʾ����ķ���
int count1=0,k=0,flag=0,conterr=0,lpnum=0;
string expressionAnalysis();//���ʽ���������ʽ���м�����ʾ
string termAnalysis();//����������ʽ���м�����ʾ
string factorAnalysis();//���ӷ��������ʽ���м�����ʾ
int expressionAnalysis2();//���ʽ�������������ʽ���������
int termAnalysis2();//��������������ʽ���������
int factorAnalysis2();//���ӷ������������ʽ���������
struct quad{//������Ԫʽ
     string result;
     string arg1;
     string arg2;
     string op;
};
struct quad quad[50];
void map_init(){//��Ӧ��ϵ���г�ʼ��������ֻ�����˱��ʽ����ط���
    word["+"]="plus";
    word["-"]="minus";
    word["*"]="times";
    word["/"]="slash";
    word["="]="eql";
    word["("]="lparen";
    word[")"]="rparen";
}
void lexanalysis(){//�ʷ�����
    char ch;
    char a;
    string word1;//string����ʶ�𵥴�
    string str;//string���������ַ�ʶ��
    ostringstream buf;
    while(buf&&infile2.get(ch)) buf.put(ch);//���ļ��е��ַ�������
    str= buf.str();//���õ����ַ����浽string���ͱ�����
    int csize=str.length();
    for(int i=0;i<csize;i++){//�������ַ������б���
        while(str[i]==' '||str[i]=='\n') i++;//���ʼΪ�ո���з�����ָ���λ��������
        if(isalpha(str[i])){//�Ա�ʶ���ͻ����ֽ���ʶ��,���ÿ⺯��isalpha()
            word1=str[i++];
            while(isalpha(str[i])||isdigit(str[i])){
                word1+=str[i++];
            }
            it=word.find(word1);
            if(it!=word.end()){//�ж��ǲ��ǻ����֣���Ϊ��������������
                outfile<<"("<<word[word1]<<","<<word1<<")"<<endl;
            }
            else{//����ֱ�����
                outfile<<"(ident"<<","<<word1<<")"<<endl;
            }
            i--;
        }
        else if(isdigit(str[i])){//�ж��ǲ��ǳ��������ÿ⺯��isdigit()
            word1=str[i++];
            while(isdigit(str[i])){
                word1+=str[i++];
            }
             if(isalpha(str[i])){
                outfile<<"error"<<endl;
                break;
            }
            else{
                outfile<<"(number"<<","<<word1<<")"<<endl;
            }
            i--;
        }else{//�������Ļ��������ν����ж�
            word1=str[i];
            it=word.find(word1);
            if(it!=word.end()){
                outfile<<"("<<word[word1]<<","<<word1<<")"<<endl;
            }else{
                outfile<<"error"<<endl;
                break;
            }
        }
    }
    infile2.close();
}
int advance(){//����������һ������
    int found1,found2;
    if(!getline(infile,str)){
        return 0;
    }
    found1=str.find(',',0);
    if(found1==-1){
        conterr++;
        cout<<"�﷨���� ʶ���ַ�����"<<endl;
        return -1;
    }
    found2=str.length();
    sym=str.substr(1,found1-1);
    sym2=str.substr(found1+1,found2-found1-2);
    return 1;
}
string newtemp(){//�����±�����t1,t2��
    char *p;
    char m[12];
    p=(char*)malloc(12);
    k++;
    itoa(k,m,10);
    strcpy(p+1,m);
    p[0]='t';
    string s;
    s=p;
    return s;
}
void emit(string op,string arg1,string arg2,string result){//������Ԫʽ������ʾ
    quad[count1].op=op;
    quad[count1].arg1=arg1;
    quad[count1].arg2=arg2;
    quad[count1].result=result;
    count1++;
    return;
}
string expressionAnalysis(){//���ʽ�ĵݹ��½���������
    string op,arg1,arg2,result;
    if(conterr){
        return NULL;
	}
	arg1=termAnalysis();//ͨ��������õ���һ��������ֵ
	if(conterr){
        return NULL;
	}
	while((sym=="plus")||(sym=="minus")){
        op=sym2;
		flag=advance();
		if(conterr){
            return NULL;
		}
		if(flag==0){
            cout<<"�﷨���� <�ӷ������>��ȱ��"<<endl;
            conterr++;
			return NULL;
		}
		arg2=termAnalysis();//ͨ��������õ��ڶ���������ֵ
		if(conterr){
            return NULL;
		}
		result=newtemp();//�����м���������൱�ڶԽ�����д洢
		emit(op,arg1,arg2,result);//������Ԫʽ���൱�ڽ��мӷ���������㣬�ó����
		arg1=result;//����õ��Ľ��
	}
	return arg1;//���ر��ʽ���յõ���ֵ
}
string termAnalysis(){//��ĵݹ��½���������
    string op,arg1,arg2,result;
    arg1=factorAnalysis();//ͨ�����ӷ����õ���һ��������ֵ
    if(conterr){
        return NULL;
    }
	while((sym=="times")||(sym=="slash")){
        op=sym2;
		flag=advance();
		if(conterr){
            return NULL;
		}
		if(flag==0){
			conterr++;
			cout<<"�﷨���� <�˷������>��ȱ����"<<endl;
			return NULL;
		}
		if(conterr){
            return NULL;
		}
		arg2=factorAnalysis();//ͨ�����ӷ����õ��ڶ���������ֵ
		if(conterr){
            return NULL;
		}
		result=newtemp();//�����м���������൱�ڶԽ�����д洢
		emit(op,arg1,arg2,result);//������Ԫʽ���൱�ڽ��г˷���������㣬�ó����
		arg1=result;//����õ��Ľ��
	}
	return arg1;//���������յõ���ֵ
}
string factorAnalysis(){
    string arg;
    if(sym=="ident"){//����Ǳ�ʶ�������շ��ر�ʶ���ķ���
        arg=sym2;
        flag=advance();
        if(conterr){
            return NULL;
		}
		if(lpnum==0&&sym=="rparen"){
            conterr++;
			cout<<"�﷨���� ')'��ƥ��"<<endl;
			return NULL;
        }
    }
    else if(sym=="number"){//������޷������������շ�����Ӧ������
        arg=sym2;
        flag=advance();
        if(conterr){
            return NULL;
		}
		if(lpnum==0&&sym=="rparen"){
            conterr++;
			cout<<"�﷨���� ')'��ƥ��"<<endl;
			return NULL;
        }
    }
    else if(sym=="lparen"){//����������ţ���Ҫ����������ƥ�䣬���ж��м��ǲ��Ǳ��ʽ�����ó����ʽ��ֵ���з���
        lpnum++;
        flag=advance();
        if(conterr){
            return NULL;
		}
		if(flag==0){
			conterr++;
			cout<<"�﷨���� '('��ȱ�ٱ��ʽ"<<endl;
			return NULL;
		}
        arg=expressionAnalysis();
        if(conterr){
            return NULL;
		}
        if(flag==0||sym!="rparen"){
			conterr++;
			cout<<"�﷨���� ���ʽ����ȱ��')'"<<endl;
			return " ";
		}else{
		    lpnum--;
            flag=advance();
            if(conterr){
                return NULL;
            }
            if(flag==0){
                return arg;
            }
		}
    }else{
		cout<<"�﷨���� �����ײ���Ϊ<��ʶ��>|<�޷�������>|'('"<<endl;
		conterr++;
		return " ";
	}
	return arg;
}
int expressionAnalysis2(){//���ʽ�ĵݹ��½���������
    string op;
    int arg1,arg2,result;
    if(conterr){
        return 0;
	}
	arg1=termAnalysis2();//ͨ��������õ���һ��������ֵ
	if(conterr){
        return 0;
	}
	while((sym=="plus")||(sym=="minus")){
        op=sym2;
		flag=advance();
		if(conterr){
            return 0;
		}
		if(flag==0){
            cout<<"�﷨���� <�ӷ������>��ȱ��"<<endl;
            conterr++;
			return 0;
		}
		arg2=termAnalysis2();//ͨ��������õ��ڶ���������ֵ
		if(conterr){
            return 0;
		}
		if(op=="+"){//���Ǽӷ���������мӷ����㣬���Եõ��Ľ�����б���
            result=arg1+arg2;
            arg1=result;
		}
        else{//���Ǽ�����������мӷ����㣬���Եõ��Ľ�����б���
            result=arg1-arg2;
            arg1=result;
        }
	}
	return arg1;//���ظñ��ʽ�������ֵ
}
int termAnalysis2(){//��ĵݹ��½���������
    string op;
    int arg1,arg2,result;
    arg1=factorAnalysis2();//ͨ�����ӷ����õ���һ��������ֵ
    if(conterr){
        return 0;
    }
	while((sym=="times")||(sym=="slash")){
        op=sym2;
		flag=advance();
		if(conterr){
            return 0;
		}
		if(flag==0){
			conterr++;
			cout<<"�﷨���� <�˷������>��ȱ����"<<endl;
			return 0;
		}
		if(conterr){
            return 0;
		}
		arg2=factorAnalysis2();//ͨ�����ӷ����õ��ڶ���������ֵ
		if(conterr){
            return 0;
		}
		if(op=="*"){//���ǳ˷���������мӷ����㣬���Եõ��Ľ�����б���
            result=arg1*arg2;
            arg1=result;
		}
        else{//���ǳ�����������мӷ����㣬���Եõ��Ľ�����б���
            if(arg2==0){
                conterr++;
                cout<<"��������Ϊ0"<<endl;
                return 0;
            }
            result=arg1/arg2;
            arg1=result;
        }
	}
	return arg1;//���ظ����������ֵ
}
int factorAnalysis2(){
    int arg;
    if(sym=="ident"){//�������ʽ�в�������ĸ�������޷���������
        cout<<"�������ʽ�к�����ĸ"<<endl;
		conterr++;
		return 0;
    }else if(sym=="number"){//���������֣��򷵻���Ӧ��ֵ
        arg=atoi(sym2.c_str());
        flag=advance();
        if(conterr){
            return 0;
		}
		if(lpnum==0&&sym=="rparen"){
            conterr++;
			cout<<"�﷨���� ')'��ƥ��"<<endl;
			return 0;
        }
    }
    else if(sym=="lparen"){//����������ţ���Ҫ����������ƥ�䣬���ж��м��ǲ��Ǳ��ʽ�����ó����ʽ��ֵ���з���
        lpnum++;
        flag=advance();
        if(conterr){
            return 0;
		}
		if(flag==0){
			conterr++;
			cout<<"�﷨���� '('��ȱ�ٱ��ʽ"<<endl;
			return 0;
		}
        arg=expressionAnalysis2();//���ر��ʽ��ֵ
        if(conterr){
            return 0;
		}
        if(flag==0||sym!="rparen"){
			conterr++;
			cout<<"�﷨���� ���ʽ����ȱ��')'"<<endl;
			return 0;
		}else{
		    lpnum--;
            flag=advance();
            if(conterr){
                return 0;
            }
            if(flag==0){
                return arg;
            }
		}
    }else{
		cout<<"�﷨���� �����ײ���Ϊ<��ʶ��>|<�޷�������>|'('"<<endl;
		conterr++;
		return 0;
	}
	return arg;//���ظ������������ֵ
}
int main(){
    int i=0,num,result;
    //��ʼ�ʷ�����
    map_init();
    lexanalysis();
    //��ʼ�﷨���������
    cout<<"1.PL/0���ʽ���м�����ʾ"<<endl;
    cout<<"2.PL/0�������ʽ���������"<<endl;
    cout<<"������������:";
    cin>>num;
    flag=advance();
    if(num==1){//PL/0���ʽ���м�����ʾ
        if(flag){
            expressionAnalysis();//��ʼ���б��ʽ����
        }
        if(flag!=-1&&!conterr){//�����ʽ��ȷ��������ʽ���м�����ʾ
            for(int i=0;i<count1;i++){
                cout<<'('<<quad[i].op<<','<<quad[i].arg1<<','<<quad[i].arg2<<','<<quad[i].result<<')'<<endl;;
            }
        }
    }else if(num==2){//PL/0�������ʽ���������
        if(flag){
            result=expressionAnalysis2();//��ʼ���б��ʽ����
        }
        if(flag!=-1&&!conterr){//�����ʽ��ȷ��������ʽ��ֵ
            cout<<result<<endl;
        }
    }else{
       cout<<"error!"<<endl;
       return 0;
    }
    infile.close();
    return 0;
}

