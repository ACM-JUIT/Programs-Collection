#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
struct DoublyList
{
        int data;
        struct DoublyList *prev;
        struct DoublyList *next;
};
typedef struct DoublyList DoublyList;
void insertAtBeginning (DoublyList **, DoublyList **);
void insertAtEnd (DoublyList **, DoublyList **);
void insertAfterElement(DoublyList **, DoublyList **);
void displayInorder(DoublyList *);
void displayReverseOrder(DoublyList*);
void insertBeforeElement(DoublyList**);


void main()
{
//         clrscr();
        DoublyList *head = NULL;
        DoublyList *tail = NULL;
        insertAtBeginning (&head, &tail);
        insertAtBeginning (&head, &tail);
        insertAtBeginning (&head, &tail);


        displayInorder(head);


        insertAtEnd (&head, &tail);
        insertAtEnd (&head, &tail);
        insertAtEnd (&head, &tail);


        displayInorder(head);


        insertAfterElement(&head, &tail);
        displayInorder(head);


        insertBeforeElement(&head);
        displayInorder(head);


        getch();
}


// Insert at the beginning of the list
void insertAtBeginning (DoublyList **head, DoublyList **tail)
{
        int d;
        printf("\n Enter the data: ");
        scanf("%d", &d);
        DoublyList *node =(DoublyList*) malloc (sizeof(DoublyList));
        node->data = d;
        if(*head==NULL)
        {
                node->prev=NULL;
                node->next = NULL;
                *head = *tail = node;
        }
        else
        {
                node->prev = NULL;
                node->next = *head;
                (*head)->prev = node;
                *head = node;
        }
}
void insertAtEnd (DoublyList **head, DoublyList **tail)
{
        int d;
        printf("\n Enter the data: ");
        scanf("%d", &d);
        DoublyList *node = (DoublyList*)malloc(sizeof(DoublyList));
        node->data = d;
        if(*head == NULL)
        {
                node->prev = NULL;
                node->next = NULL;
                *head = *tail = node;
        }
        else
        {
                node->next=NULL;
                node->prev=*tail;
                (*tail)->next=node;
                *tail=node;
        }
}
void displayInorder(DoublyList *head)
{
        printf("\nThe elements in the list are: ");
        while(head != NULL)
        {
                printf("%d\t", head->data);
                head = head->next;
        }
}


void displayReverseOrder(DoublyList* tail)
{
        printf("\nThe elements in the list are: ");
        while(tail != NULL)
        {
                printf("%d\t", tail->data);
                tail = tail->prev ;
        }
}
        
DoublyList *search(DoublyList* head, int searchelement)
{
        while(head != NULL)
        {
                if(head->data == searchelement)
                        return head;
                head =head->next;
        }
        return NULL;
}
// Inserting after a given element
void insertAfterElement (DoublyList **head, DoublyList **tail)
{
                DoublyList *node;
                DoublyList *loc;
                DoublyList *temp = *head;
                int d;
                int searchelement;
                printf("\n Enter the data to be inserted: ");
                scanf("%d",&d);
                printf("\n Enter the search element after which the node has to be inserted: ");
                scanf("%d", &searchelement);




                loc=search(temp,searchelement);
                node = (DoublyList *)malloc(sizeof(DoublyList));
                node->data = d;
                if(loc == NULL)
                        return;
                if(loc->next == NULL)
                {
                        node->next = NULL;
                        loc->next=node;
                        node->prev=*tail;
                        *tail=node;
                }
                else
                {
                        node->prev=loc;
                        node->next=loc->next;
                        (loc->next)->prev=node;
                        loc->next=node;
                }
}


void insertBeforeElement(DoublyList **head)
{
        int d;
        int searchelement;
        DoublyList *node, *loc;
        DoublyList *temp = *head;
        printf("\n Enter the data to be inserted: ");
        scanf("%d", &d);
        printf("\n Enter the search element after which the node has to be inserted: ");
        scanf("%d", &searchelement);
        loc=search(temp, searchelement);
        if(loc==NULL)
                return;
        node=(DoublyList*) malloc(sizeof(DoublyList));
                node->data=d;
        if(loc->prev==NULL)
        {
                node->prev=NULL;
                loc->prev=node;
                node->next=*head;
                *head=node;
        }
        else
        {
                node->prev=loc->prev;
                node->next=loc;
                (loc->prev)->next = node;
                loc->prev=node;
        }
}


void deleteAtBegining(DoublyList **head, DoublyList **tail)
{
        DoublyList *temp;
        if(*head==NULL)
                return;
        temp = *head;
        if(*head == *tail)  /*one element only*/
               *head=*tail=NULL;
        else
        {
                (temp->next)->prev=NULL;
                *head=temp->next;
        }
        free(temp);
}
void deleteAtEnd(DoublyList **head, DoublyList **tail)
{
        DoublyList *temp;
        if(*head==NULL)
                return;
        temp = *tail;
        if(*head == *tail)       /*one element only*/
                *head=*tail=NULL;
        else
        {
                (temp->prev)->next=NULL;
                *tail=temp->prev;
        }
        free(tail);
}
void deleteAfterElement(DoublyList **head, DoublyList **tail)
{
        DoublyList *temp, *loc;
        temp = *head;
        int d;
        int searchelement;
        printf("\n Enter the data to be inserted: ");
        scanf("%d", &d);
        printf("\n Enter the search element after which the node has to be deleted: ");
        scanf("%d", &searchelement);
        loc = search(temp, searchelement);
        if(loc==NULL)
                return;
        else if(loc->next->next==NULL)
        {
                temp=loc->next;
                loc->next=NULL;
                *tail=loc;
                free(temp);
        }
        else
        {
                temp =loc->next;
                loc->next=temp->next;
                (temp->next)->prev=loc;
                free(temp);
        }
}
// void deleteAfterelement (node **head, node **tail, int item, int before)
//         {
//                 node *ptr, *loc;
//                 ptr=head;
//                 loc=search(ptr,before);
//                 if(loc==NULL)
//                         return;
//                 else if((loc->prev)->prev==NULL)
//                         {
//                         ptr=loc->prev;
//                         loc->prev=NULL;
//                         *head=loc;
//                         free(ptr);
//                         }
//                          else
//                         {
//                         ptr=loc->prev;
//                         loc->prev=ptr->prev;
//                         (ptr->prev)->next=loc;
//                         free(ptr);
//                         }
// }


// void deletelist(node **head, node **tail)
// {
//         node *ptr;
//         while(*head!=NULL)
//         {
//                 ptr=*head;
//                 *head=(*head)->next;
//                 free(ptr);
//         }
//         *tail=NULL;
// }