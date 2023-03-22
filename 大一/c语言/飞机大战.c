#include<stdio.h>
#include<stdlib.h>
#include<conio.h>
#include<windows.h>
int sorce;	//分数
int position_x, position_y;//飞机位置
int enemy_x, enemy_y;//敌机位置
int bullet_x, bullrt_y;//子弹位置
int high, width;//游戏画面尺寸
int initial;//敌机初始速度
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
	initial = 10;
	sorce = 0;
	high =28;
	width = 40;
	position_x = high / 2;
	position_y = width / 2;
	bullet_x = -1;
	enemy_x = 0;
	enemy_y = width / 2;
}
void gotoxy(int x, int y)
{
	COORD pos = { x,y };
	HANDLE hOut = GetStdHandle(STD_OUTPUT_HANDLE);// 获取标准输出设备句柄
	SetConsoleCursorPosition(hOut, pos);//两个参数分别是指定哪个窗体，具体位置
}

void show()		//显示画面
{
	int i, j;

	gotoxy(0, 0);
	for (i = 0; i < high; i++)
	{
		
		for (j = 0; j < width; j++)
		{
			 if ((i == position_x) && (j == position_y))
				printf("*");//输出飞机*
			else if ((i == bullet_x) && (j == bullrt_y))
				printf("|");//输出子弹
			else if ((i == enemy_x) && (j == enemy_y))
				printf("@");//输出敌机
			else
				printf(" ");//输出空格
			 
	
				
		}
		printf("\n");//换行
	}
	printf("分数:%d\n",sorce);//显示分数
}
void updateWithoutInput()		//与用户输入无关的更新
{
	hidden();
	if(bullet_x>-1)
			bullet_x--;
	static int speed = 0;
	if (speed < initial)	//敌机速度
		speed++;
	if (speed == initial) 
	{

		speed = 0;
		if (enemy_x > high)	//随机产生敌机
		{
			enemy_x = 0;
			enemy_y = rand()%width;
		}
		else
			enemy_x++;
		
	}
	if ((bullet_x == enemy_x) && (bullrt_y == enemy_y))	//击杀敌机，分数加1
	{
		enemy_x = 0;
		enemy_y = rand() % width;
		sorce++;
	}
	if (sorce > 10 && speed>8)
	{
		initial = initial - 5;
		speed = 0;
	}
}
void updateWithInput()		//与用户输入有关的更新
{
	char input;
	if (_kbhit())		//当按键进行时
	{
		input = _getch();
		if (input == 'w')
			position_x--;
		if (input == 's')
			position_x++;
		if (input == 'a')
			position_y--;
		if (input == 'd')
			position_y++;
		if (input == ' ')
		{
			bullet_x = position_x - 1;
			bullrt_y = position_y;
		}
	}

}
int main()
{
	startup();	//数据初始化
	while (1) 
	{
		if (((position_x == enemy_x) && (position_y == enemy_y)) || enemy_x>high )//当没有击杀敌机或碰到敌机时，游戏结束
		{
			printf("\nyou die\n");
			break;

		}
		show();		//显示画面
		updateWithoutInput();	//与用户输入无关的更新
		updateWithInput();		//与用户输入有关的更新
		Sleep(20);
		
	}
	
	 return 0;
}

