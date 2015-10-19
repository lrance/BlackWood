/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.nodak.ndsu.cs.lions.blackwood.GUI;

import java.awt.Graphics;

/**
 *
 * @author orockon  
 */
public class BarChart {

    private String [][] information;
    private int max,width,height,elements,current,x,y, numShown;
    public BarChart(){
        information=new String[0][];
        max=getMax();
        x=0;
        y=0;
        width=0;
        height=0;
        elements=0;
        current=0;
        numShown=0;
    }

    public BarChart(int xp, int yp, int w, int h,String[][] info){
        information=info;
        max=getMax();
        x=xp;
        y=yp;
        width=w;
        height=h;
        elements=info.length;
        current=0;
        numShown=numShown();
    }

    public void paint(Graphics g){
        if(elements>0){
        g.drawLine(x+20, y, x+20, y+height-10);
        g.drawLine(x+20,y+height-10,x+20+width,y+height-10);
        g.drawString(max+"",x,y);
        g.drawString("0", x, y+height-10);
        int temp;
        if(numShown==1){
            temp=(width-20)/2;
        } else
            temp=(width)/(numShown+1);
        int space=temp;
        double scale = (((double)height-10)/(double)max);
        for(int i=0; i<numShown;i++){
            if(information.length>(current+i)){
            String[] s= information[current+i];
            g.fillRect(x+space,((y+(height-10))-(int)(Integer.parseInt(s[1])*scale)), 30, (int)(Integer.parseInt(s[1])*scale));
            g.drawLine(x+space,y+height-15, x+space, y+height-5);
            space+=temp;
            g.drawString("Bar "+(i+1)+": "+s[0],x+20,y+height+10+(15*i));
            }
        }
        /*for(String[] s:information){
            g.drawString(s[1],30,y);
            g.drawString(s[2],60,y);
            y +=40;
        }
        g.drawString(max+"",400,400);
        g.drawString(width+"",400,410);*/
        }
    }

    private int getMax(){
        int temp=0;
        for(String[] s:information){
            int i=Integer.parseInt(s[1]);
            if(i>temp)
                temp=i;
        }
        return temp;
    }

    public void next(){
        int pos= current+numShown;
        if((pos)<elements){
            current=pos;
            numShown=numShown();
        }
    }

    public void previous(){
        if(current>0){
            int num=1;
            String[] temp= information[current-1];
            int pos = current-1;
            int i=temp[0].indexOf(' ');
            if(i<=0){
                i=temp[0].length();
            }
        String s=temp[0].substring(0, i);
        while(true){
            if(pos>0){
                String[] temp2=information[pos-1];
                int h= temp2[0].indexOf(' ');
                if(h<=0){
                    h=temp2[0].length();
                }
                if(h==i){
                    if(temp2[0].substring(0,h).equals(s)){
                        pos--;
                        num++;
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }else
                break;
        }
        current=pos;
        numShown=num;
        }
    }

    public void setInfo(String[][] s){
        information=s;
        getMax();
        elements=s.length;
    }

    private int numShown() {
        int num=1;
        String[] temp= information[current];
        int pos = current+1;
        //if(information.length>pos){
            int i=temp[0].indexOf(' ');
            if(i<=0){
                i=temp[0].length();
            }
            String s=temp[0].substring(0, i);
            while(true){
                if(pos>=information.length)
                    break;
                String[] temp2=information[pos];
                int h= temp2[0].indexOf(' ');
                if(h<=0){
                    h=temp2[0].length();
                }
                if(h==i){
                    if(temp2[0].substring(0,h).equals(s)){
                        pos++;
                        num++;
                    }else{
                        break;
                    }
                }else{
                    break;
                }
            }
        //}
        return num;
    }

}
