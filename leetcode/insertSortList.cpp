/**

 * Definition for singly-linked list.

 * struct ListNode {

 *     int val;

 *     ListNode *next;

 *     ListNode(int x) : val(x), next(NULL) {}

 * };

 */

class Solution {

public:

    ListNode *insertionSortList(ListNode *head) {

        if (head ==NULL || head->next == NULL)

            return head;

        ListNode *p=head->next;

        ListNode *prev = head;

        while(p != NULL){

            if(p->val < head->val){

                prev->next = p->next;

                p->next = head;

                head = p;

            }

            else if(p->val < prev->val){

                ListNode *q = head;

                while(q->next->val <= p->val && q!=NULL && q!=prev){

                    q = q->next;

                }

                if(q!=prev && q!=NULL){

                    prev->next = p->next;

                    p->next = q->next;

                    q->next = p;

                }

            }

            else{

                prev = p;

            }

            p = prev->next;

        }

        return head;

    }

};
