# include<stdio.h>

int main(){
    printf("%d", fibonacci());
    return 0;
}
int fibonacci(){
    int a, f=0, f1=0, f2=1;
    printf("enter the number of terms : ");
    scanf("%d", &a);
   
    for(int i=1;i<=a;i++){
        if(i==1){
            printf("%d\n",f1);
        }
        if(i==2){
            printf("%d\n",f2);
        }

        f = f1 + f2;
        f1 = f2;
        f2 = f; 
        printf("%d\n", f);
    }
        
    
}
    

    