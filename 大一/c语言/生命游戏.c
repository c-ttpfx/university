#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<windows.h>
#define high 30
#define width 50
int cell[high][width];
int i, j;
void hidden()
{
	HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);
	CONSOLE_CURSOR_INFO cci;
	GetConsoleCursorInfo(hOut, &cci);
	cci.bVisible = FALSE;
	SetConsoleCursorInfo(hOut, &cci);
}
void startup()		//数据初始化
{
	for (i = 0; i < high; i++)
	{
		for (j = 0; j < width; j++)
			cell[i][j] = rand() % 2;

	}

}
void gotoxy(int x, int y)
{
	COORD pos = { x,y };
	HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);// 获取标准输出设备句柄
	SetConsoleCursorPosition(hOut, pos);//两个参数分别是指定哪个窗体，具体位置
}

void show()		//显示画面
{
	gotoxy(0,0);
	hidden();
	
	for (i = 0; i < high-1; i++)
	{
		for (j = 0; j < width-1; j++)
		{
			if (cell[i][j] == 1)
				printf("*");
			else
				printf(" ");
		}
		printf("\n");
	}
	Sleep(50);
}
void updateWithoutInput()		//与用户输入无关的更新
{
	
	int temp[high][width];
	for (i = 0; i < high; i++)
	{
		for (j = 0; j < width; j++)
			temp[i][j] = cell[i][j];
	}
	int n = 0;
	for (i = 1; i < high - 1; i++)
	{
		for (j = 1; j < width - 1; j++)
		{
			n = cell[i - 1][j - 1] + cell[i][j - 1] + cell[i + 1][j - 1] + cell[i - 1][j] + cell[i][j] + cell[i + 1][j] + cell[i - 1][j + 1] + cell[i][j + 1] + cell[i + 1][j + 1];
			if (n == 3)
				temp[i][j] = 1;
			else if (n == 2|| n==1)
				temp[i][j] = cell[i][j];
			else
				temp[i][j] = 0;
		}
	}
	for (i = 0; i < high; i++)
	{
		for (j = 0; j < width; j++)
			cell[i][j] = temp[i][j];
	}
}
void updateWithInput()		//与用户输入有关的更新
{

}
int main()
{
	startup();	//数据初始化
	while (1)
	{

		show();		//显示画面
		updateWithoutInput();	//与用户输入无关的更新
		updateWithInput();		//与用户输入有关的更新
		Sleep(20);

	}

	return 0;
}

