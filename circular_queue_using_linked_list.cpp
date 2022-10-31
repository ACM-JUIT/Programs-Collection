/*
    C++ program : Circular Queue using linked list
*/
# include<bits/stdc++.h>
using namespace std;


class node
{
public:
    int data;
    node *next;
    node()
    {
        next = NULL;
    }
}*front=NULL,*rear=NULL,*n,*temp,*temp1;

class cqueue
{
public:
    void insertion();
    void deletion();
    void display();
};

void cqueue::insertion()
{
    n=new node[sizeof(node)];
    cin>>n->data;
    if(front==NULL)
    {
        front=n;
    }
    else
    {
        rear->next=n;
    }
    rear=n;
    rear->next=front;
}

void cqueue::deletion()
{
    int x;
    temp=front;
    if(front==NULL)
    {
        cout<<"\n\tCircular Queue Empty!!!";
    }
    else
    {
        if(front==rear)
        {
            x=front->data;
            delete(temp);
            front=NULL;
            rear=NULL;
        }
        else
        {
        x=temp->data;
        front=front->next;
        rear->next=front;
        delete(temp);
        }
        cout<<"\n\tElement "<<x<<" is Deleted";
        display();
    }
}

void cqueue::display()
{
    temp=front;
    temp1=NULL;
    if(front==NULL)
    {
        cout<<"\n\tCircular Queue Empty!!!";
    }
    else
    {
        cout<<"\n\tCircular Queue Elements are:\n\t";
        while(temp!=temp1)
        {
            cout<<temp->data<<"  ";
            temp=temp->next;
            temp1=front;
        }
    }
}

int main()
{
    cqueue cqobj;
    int i=0;
    int choice;
    do
    {
        system("cls");
        cout<<"\t\t**********MENU**********\n\tBasic Circular Queue (array implementation) Operations:";
        cout<<"\n\t1.Insert";
        cout<<"\n\t2.Delete";
        cout<<"\n\t3.Display";
        cout<<"\n\t4.Exit";
        cout<<"\n\nEnter your choice: ";
        cin>>choice;
        char ch;
        switch(choice)
        {
            case 1:
                do{
                    int n;
                    cout<<"\n\tNumber of elements to enter: ";
                    cin>>n;
                    n=n+i;
                    cout<<endl;
                    for(; i<n; i++)
                    {
                        cout<<"\tElement #"<<i+1<<" :";
                        cqobj.insertion();
                    }
                    cqobj.display();
                    cout<<"\nInsert more?(y/n):";
                    cin>>ch;
                }while(ch=='y' || ch=='Y');
                break;

            case 2:
                do{
                    cqobj.deletion();
                    i--;
                    cout<<"\nDelete more?(y/n):";
                    cin>>ch;
                }while(ch=='y' || ch=='Y');
                break;

            case 3:
                cqobj.display();
                break;

            case 4:
                break;

            default:
                cout<<"\n\nWrong Choice! Try Again.";
        }
        cout<<"\nPress any key to continue...";
        getch();
    }while(choice!=4);
    return 0;
}
