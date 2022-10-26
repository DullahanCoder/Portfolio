#include<stdio.h>
#include<termios.h>
#include "terminal.c"
#include<time.h>

#define MAX_DISPLAY_LENGHT = 7

int indicatorMatrix[4][3];
char arrayMod1[12] = {'+','0','=','1','2','3','4','5','6','7','8','9'};
char arrayMod2[12] = {'9','8','7','6','5','4','3','2','1','+','0','='};
int keyboardMod = 0, total = 0, display = 0;
char filename;
FILE *file = NULL;

int select(){
    system("clear");
    for(int i = 0 ; i < 4 ; i++){
        for(int j = 0 ; j < 3; j++){
            if(indicatorMatrix[i][j] == 1){
                int position = i*3+j;
                char c = keyboardMod == 0 ? arrayMod1[position] : arrayMod2[position];
                switch(c){
                    case 43: //+
                        total += display;
                        display = 0;
                        break;
                    case 61: //=
                        return 1;
                    default:
                        if(c >= 48 && c <= 57){//Digits, 0-9
                            if(display*10 < 9999999){
                                display = (display * 10) + (c-'0');
                            }
                        }
                    }
                return 0;
            }
        }
    }
}

void moveIndicatorBy(int row, int column){
    system("clear");
    for(int i = 0 ; i < 4 ; i++){
        for(int j = 0 ; j < 3; j++){
            if(indicatorMatrix[i][j] == 1){
                if(j+column < 3 && j+column >= 0 && i + row >=0 && i+row <4){
                    indicatorMatrix[i][j]=0;
                    indicatorMatrix[i+row][j+column]=1;
                    }
                    return;
                }
            }
        }
}

void indicatorMatrixStartUp(){
    for(int i = 0 ;i < 4; i++){
        for(int j = 0 ; j < 3; j++){
            if( i == 0 && j == 0 ){
                indicatorMatrix[i][j]=1;
            } else {
                indicatorMatrix[i][j]=0;
            }
        }
    }
}

void randomKeyboard()
{   
    srand(time(0));
    int randomInt = rand();
    keyboardMod = randomInt%2 == 0 ? 0:1;

}

void createCalculator(){
    //Display Rows
    for(int i = 0 ; i < 3; i++){
        for(int j = 0 ; j < 9 ; j++){
            //Middle display row
            if(i==1){
                if(j==0){
                    printf("|%7d|",display);
                }
            }
            //Upper and lower border
            else {        
                printf("-");
            }
        }
        printf("\n");
    }

    //Empty Row
    printf("\n");
    
    //Keyboard + INDICATOR
    for(int i = 0 ; i < 4; i++){
        //Space before rows
        printf("  ");
        for(int j = 0 ; j < 3 ; j++){
            //Keyboard
            if(keyboardMod==0){
                printf("%c ",arrayMod1[(i*3+j)]);
            } else {
                printf("%c ",arrayMod2[(i*3+j)]);
            }
        }

        //Space between rows
        printf("\n  ");

        //Indicator Position
        for(int j = 0 ; j < 3; j++){
            if(indicatorMatrix[i][j]==0){
                printf("  ");
            } else {
                printf("^");
            }
        }
        printf("\n");
    }

    printf("\nCurrent total: %d\n",total);
}


int main(int argc, char *argv[]){

    
    //Argument file  
    if (argc < 2)
    {
        printf("Please prove argument correctly: ./calculator <filename>\n");
        return(1);
    }
    else
    {
        file = fopen(argv[1], "wb");
        if (!file)
            error();
    }

    randomKeyboard();
    indicatorMatrixStartUp();
    disableBuffer();

    while(1){

    createCalculator();

    int c;   
  
    switch( c = getchar() ){
        case 97: //a, left
            moveIndicatorBy(0,-1);
            break;
        case 100://d, right
            moveIndicatorBy(0,1);
            break;
        case 119://s, down
            moveIndicatorBy(-1,0);
            break;
        case 115://w, up
            moveIndicatorBy(1,0);
            break;
        case 101://e
            if(select() == 1){
                enableBuffer();
                fprintf(file, "%d",total);
                return 0;
            }
            break;
        default:
            system("clear");
            break;
    }

    }
    return 0;
}