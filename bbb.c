int main() {
    int a=10;
    int b=5;
    int c=3;
    int d=!a;
    if(c<a<b)
    {
    	d=1;
    	if(b<=5)
    	{
    		d=2;
    		if(c<3)
    		{
    			d=3;
    		}
    		//d=4;
    		else if(c==3)
    		{
    			d=5;
    		}
    		else
    		{
    			d=6;
    		}
    		d=7;
    	}
    //	d=8;
    	else{
    	d=9;
    	}
    	return d;
    }
  else
  	{
  		d=11;
  		return d;
	  }
	  return d;
}
