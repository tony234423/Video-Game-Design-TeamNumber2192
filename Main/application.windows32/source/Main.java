import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.sound.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Main extends PApplet {


player player1;
int levelCap=9;
SoundFile fireballSound;
SoundFile swordStrike;
SoundFile longSwordStrike;
SoundFile extraSwordStrike;
SoundFile swordHurt1;
SoundFile swordHurt2;
SoundFile swordStrike2;
SoundFile swordStrike3;
SoundFile hurtBat;
SoundFile hurtSpider;
SoundFile hurtDragon;
SoundFile hurtGolem1;
SoundFile hurtGolem2;
obstacle obstacles[][]= new obstacle[levelCap+1][7];//the jagged arrays that can be used in java do not work here for some reason,so I have to use dummy variables

PImage playerTop;
PImage playerTop1;
PImage playerTop2;
PImage playerTop3;
PImage playerTop4;
PImage playerTop5;
PImage playerBottom;
PImage playerBottom1;
PImage playerBottom2;
PImage playerBottom3;
PImage playerBottom4;
PImage playerBottom5;
PImage playerLeft;
PImage playerLeft1;
PImage playerLeft2;
PImage playerLeft3;
PImage playerLeft4;
PImage playerLeft5;
PImage playerRight;
PImage playerRight1;
PImage playerRight2;
PImage playerRight3;
PImage playerRight4;
PImage playerRight5;
PImage playerTopHurt;
PImage playerRightHurt;
PImage playerLeftHurt;
PImage playerBottomHurt;
PImage img_shortsword;
PImage img_shortsword_rotate;
PImage img_longsword;
PImage img_longsword_rotate;
PImage shortsword_back;
PImage shortsword_left;
PImage longsword_back;
PImage longsword_left;
PImage sword;
PImage sword_rotate;
PImage sword_left;
PImage sword_back;
PImage health;
PImage damage;
PImage range;
PImage movement;
PImage caveDoor;
PImage caveDoorLeft;
PImage lightDoor;
PImage meleeEnemyLow;
PImage meleeEnemy;
PImage meleeEnemyHigh;
PImage enemyHurt;
PImage swordEnemy;
PImage swordEnemyLeft;
PImage swordEnemyRight;
PImage swordEnemyHurt;
PImage boss1;
PImage boss2;
PImage spikedWall;
PImage spikedWallVer;
PImage spikedWallHor;
PImage normalVer;
PImage normalHor;
PImage normal;
PImage spider;
PImage spiderLeft;
PImage spiderRight;
PImage spiderHurt;
PImage golem;
PImage golemLeft;
PImage golemRight;
PImage golemHurt;
PImage riderLeft;
PImage rider;
PImage riderRight;
PImage riderHurt;
PImage dragonLeft;
PImage dragon;
PImage dragonRight;
PImage dragonHurt;
PImage bossLeft;
PImage boss;
PImage bossRight;
PImage bossHurt;
PImage fireball;
PImage trident;
PImage trident_rotate;
PImage trident_left;
PImage trident_back;

PImage[] background= new PImage[10];
button[] buttons=new button[2];
enemy[][] enemies = new enemy[levelCap+1][4];
powerup[][] powerups= new powerup[levelCap+1][1];
boolean paused;
int level;//one less than the level number, since this variable starts from 0 at the first level
boolean won;
int frames;

powerup[] powerupshop= new powerup[3];
powerup[] powerupshop2= new powerup[3];
public void setup(){
  playerTop=loadImage("standing_forward_player.png");
  playerTop1=loadImage("step1_forward_player.png");
  playerTop2=loadImage("step2_forward_player.png");
  playerTop3=loadImage("step3_forward_player.png");
  playerTop4=loadImage("step4_forward_player.png");
  playerTop5=loadImage("step5_forward_player.png");
  playerBottom= loadImage("standing_backward_player.png");
  playerBottom1= loadImage("step1_backward_player.png");
  playerBottom2= loadImage("step2_backward_player.png");
  playerBottom3= loadImage("step3_backward_player.png");
  playerBottom4= loadImage("step4_backward_player.png");
  playerBottom5= loadImage("step5_backward_player.png");
  playerRight= loadImage("standing_rightward_player.png");
  playerRight1=  loadImage("step1_rightward_player.png");
  playerRight2=  loadImage("step2_rightward_player.png");
  playerRight3=  loadImage("step3_rightward_player.png");
  playerRight4=  loadImage("step4_rightward_player.png");
  playerRight5=  loadImage("step5_rightward_player.png");
  playerLeft= loadImage("standing_leftward_player.png");
  playerLeft1= loadImage("step1_leftward_player.png");
  playerLeft2= loadImage("step2_leftward_player.png");
  playerLeft3= loadImage("step3_leftward_player.png");
  playerLeft4= loadImage("step4_leftward_player.png");
  playerLeft5= loadImage("step5_leftward_player.png");
  playerTopHurt=loadImage("hurt_frontward_player.png");
  playerRightHurt=loadImage("hurt_rightward_player.png");
  playerLeftHurt=loadImage("hurt_leftward_player.png");
  playerBottomHurt=loadImage("hurt_backward_player.png");
  img_shortsword=loadImage("Layer1.png");
  img_shortsword_rotate=loadImage("Layer1_rotate.png");
  img_longsword=loadImage("Layer2.png");
  img_longsword_rotate=loadImage("Layer2_rotate.png");
  shortsword_back=loadImage("Layer1_back.png");
  shortsword_left=loadImage("Layer1_left.png");
  longsword_back=loadImage("Layer2_back.png");
  longsword_left=loadImage("Layer2_left.png");
   sword=loadImage("Zweihander.png");
   sword_rotate=loadImage("Zweihander_rotate.png");
   sword_left=loadImage("Zweihander_left.png");
   sword_back=loadImage("Zweihander_back.png");
  health=loadImage("Studio_Project.png");
  damage= loadImage("game_skill_ui_knife_stab_damage-512.png");
  range= loadImage("1661733-200.png");
  movement= loadImage("Winged_Shoe-512.png");
  caveDoor=loadImage("cavedoor.png");
  caveDoorLeft=loadImage("cavedoor_left.png");
  lightDoor= loadImage("light_cavedoor.png");
  spikedWall= loadImage("spike_wall_sqaure.png");
  normal= loadImage("wall_square.png");
  spikedWallHor = loadImage("wall_horizontal.png");
  spikedWallVer = loadImage("wall_vertical.png");
  normalHor=loadImage("horizontal_wall.png");
  normalVer=loadImage("vertical_wall.png");
  meleeEnemyLow=loadImage("Bat_low.png");
  meleeEnemyHigh=loadImage("Bat_high.png");
  meleeEnemy=loadImage("Bat_med.png");;
  enemyHurt=loadImage("bat_hurt.png");
   swordEnemy=loadImage("standing_goblin.png");
   swordEnemyLeft=loadImage("left_leg_goblin.png");
   swordEnemyRight=loadImage("right_leg_goblin.png");
   swordEnemyHurt=loadImage("hurt_goblin.png");
  spiderRight=loadImage("right_legs_spider.png");
  spiderLeft=loadImage("left_legs_spider.png");
  spider=loadImage("standing_spider.png");;
  spiderHurt=loadImage("hurt_spider.png");
  golemRight=loadImage("right_foot_golem.png");
  golemLeft=loadImage("left_foot_golem.png");
  golem=loadImage("standing_foot_golem.png");;
  golemHurt=loadImage("hurt_golem_ice.png");
   riderLeft=loadImage("left_rider.png");
   rider=loadImage("standing_rider.png");
   riderRight=loadImage("right_rider.png");
   riderHurt=loadImage("hurt_rider.png");
   dragonLeft=loadImage("left_leg_dragon.png");
   dragon=loadImage("standing_dragon.png");
   dragonRight=loadImage("right_leg_dragon.png");
   dragonHurt=loadImage("hurt_dragon.png");
   bossLeft=loadImage("left_leg_dragonrider.png");
   boss=loadImage("standing_dragonrider.png");
   bossRight=loadImage("right_leg_dragonrider.png"); 
   bossHurt=loadImage("hurt_dragonrider.png");
   fireball=loadImage("fireball.png");
   background[0]=loadImage("lvl1_Background.png");
   background[1]= loadImage("lvl2_Background.png");
   background[2]= loadImage("lvl3_Background.png");
   background[3]= loadImage("lvl4_Background.png");
   background[4]=loadImage("lvl5_background.png");
   background[5]=loadImage("lvl6_background.png");
   background[6]=loadImage("lvl7_background.png");
   background[7]=loadImage("lvl8_background.png");
   background[8]=loadImage("lvl9_background.png");
   background[9]=loadImage("lvl10_background.png");
  fireballSound= new SoundFile(this,"fire_noise.wav");
  swordStrike= new SoundFile(this,"SHORT_SWORD.mp3");
  swordStrike2= new SoundFile(this,"short_sword_2.mp3");
  swordStrike3= new SoundFile(this,"short_sword_3.mp3");
   trident=loadImage("up_trident.png");
   trident_rotate=loadImage("right_trident.png");
   trident_left=loadImage("left_trident.png");
   trident_back=loadImage("down_trident.png");
  longSwordStrike= new SoundFile(this,"MEDIUM_SWORD.mp3");
  extraSwordStrike= new SoundFile(this,"LONG_SWORD.mp3");
  swordHurt1=new SoundFile(this,"warfare_swords_x_2_hit_001.mp3");
  swordHurt2=new SoundFile(this,"warfare_swords_x_2_hit_002.mp3");
  hurtBat=new SoundFile(this,"hurt_bat.mp3");
  hurtSpider=new SoundFile(this,"spider_squelch_1.mp3");
  hurtDragon=new SoundFile(this,"generic_impact.mp3");
  hurtGolem1= new SoundFile(this,"golem_30.mp3");
  hurtGolem2= new SoundFile(this,"golem_70.mp3");
  background(220);
  
  frameRate(60);
  ellipseMode(CENTER);
  rectMode(CORNERS);
  imageMode(CENTER);
  noStroke();
  textAlign(CENTER);
  player1= new player(1000,250,250,5);
  obstacles[0][0]= new obstacle(0.f,0.f,200.f,50.f,false,true,false); // visually left top xpos ypos, right bottom xpos ypos, spike, invis,door
  obstacles[0][1]= new obstacle(1000.f,500.f,1200.f,700.f,true,false,false);
  obstacles[0][2]= new obstacle(1400.f,500.f,1600.f,700.f,false,false,false);
  obstacles[0][3]= new obstacle(1400.f,700.f,1600.f,900.f,true,false,false);
  obstacles[0][4]=new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[0][5]=new obstacle(width/2-150,0,width/2-100,100,true,false,false);
  obstacles[0][6]=new obstacle(width/2+100,0,width/2+150,100,true,false,false);
  obstacles[1][0]=new obstacle(width/2+100,height-50,width,height,true,false,false);
  obstacles[1][1]=new obstacle(0,height-50,width/2-100,height,true,false,false);
  obstacles[1][2]=new obstacle(0,0,width/2-100,50,true,false,false);
  obstacles[1][3]=new obstacle(0,0,50,height,true,false,false);
  obstacles[1][4]=new obstacle(width-50,0,width,height,true,false,false);
  obstacles[1][5]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[1][6]=new obstacle(width/2+100,0,width,50,true,false,false);
  obstacles[2][0]= new obstacle(0,200,1000,250,true, false, false);
  obstacles[2][1]= new obstacle(0,0,100,200,false,false,true);
  obstacles[2][2]= new obstacle(950,250,1000,900,true,false,false);
  obstacles[2][3]= new obstacle (200,850,950,900,true,false,false);
  obstacles[2][4]= new obstacle(200,400,250,850,true,false,false);
  obstacles[2][5]= new obstacle(1000,200,1700,250,true,false,false);
  obstacles[2][6]= new obstacle(1300,250,1350,800,true,false,false);
  obstacles[3][0]= new obstacle(1500,200,1600,800,false,false,false);
  obstacles[3][1]= new obstacle(200,200,300,800,false,false,false);
  obstacles[3][2]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][3]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][4]=new obstacle(width/2-150,0,width/2-100,100,false,false,false);
  obstacles[3][5]=new obstacle(width/2+100,0,width/2+150,100,false,false,false);
  obstacles[3][6]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[4][0]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][1]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][2]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][3]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][4]=new obstacle(width/2-150,0,width/2-100,100,false,false,false);
  obstacles[4][5]=new obstacle(width/2+100,0,width/2+150,100,false,false,false);
  obstacles[4][6]= new obstacle(width/2-100,0,width/2+100,100,false,false,true);
  obstacles[5][0]=new obstacle(width/2+100,height-50,width,height,false,false,false);
  obstacles[5][1]=new obstacle(0,height-50,width/2-100,height,false,false,false);
  obstacles[5][2]=new obstacle(0,0,width/2-100,50,false,false,false);
  obstacles[5][3]=new obstacle(0,0,50,height,false,false,false);
  obstacles[5][4]=new obstacle(width-50,0,width,height,false,false,false);
  obstacles[5][5]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[5][6]=new obstacle(width/2+100,0,width,50,false,false,false);
  obstacles[6][0]=new obstacle(width/2+100,height-50,width,height,true,false,false);
  obstacles[6][1]=new obstacle(0,height-50,width/2-100,height,true,false,false);
  obstacles[6][2]=new obstacle(0,0,width/2-100,50,true,false,false);
  obstacles[6][3]=new obstacle(0,0,50,height,true,false,false);
  obstacles[6][4]=new obstacle(width-50,0,width,height,true,false,false);
  obstacles[6][5]= new obstacle(width/2-100,0,width/2+100,100,true,true,true);
  obstacles[6][6]=new obstacle(width/2+100,0,width,50,true,false,false);
  obstacles[7][0]= new obstacle(width/2-10,height/2-300,width/2+10,height/2+300,false,false,false);
  obstacles[7][1]= new obstacle(width/2-500,height/2-10,width/2+500,height/2+10,false,false,false);
  obstacles[7][2]= new obstacle(0,0,0,0,false,true,false);
  obstacles[7][3]= new obstacle(0,0,0,0,false,true,false);
  obstacles[7][4]=new obstacle(width/2-150,0,width/2-100,100,false,false,false);
  obstacles[7][5]=new obstacle(width/2+100,0,width/2+150,100,false,false,false);
  obstacles[7][6]= new obstacle(width/2-100,0,width/2+100,100,false,false,true);
  obstacles[8][0]=new obstacle(width/2+100,height-50,width,height,false,false,false);
  obstacles[8][1]=new obstacle(0,height-50,width/2-100,height,false,false,false);
  obstacles[8][2]=new obstacle(0,0,width/2-100,100,false,false,false);
  obstacles[8][3]=new obstacle(0,0,50,height,false,false,false);
  obstacles[8][4]=new obstacle(width-50,0,width,height,false,false,false);
  obstacles[8][5]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[8][6]=new obstacle(width/2+100,0,width,100,false,false,false);
  obstacles[9][0]= new obstacle(0,0,width/2-100,100,false,true,false);
  obstacles[9][1]= new obstacle(width/2+50,0,width,100,false,true,false);
  obstacles[9][2]= new obstacle(width/2+100,height-100,width,height,false,false,false);
  obstacles[9][3]= new obstacle(0,height-100,width/2-100,height,false,false,false);
  obstacles[9][4]=new obstacle(0,0,width/2-50,100,false,true,false);
  obstacles[9][5]=new obstacle(width/2-50,0,width/2+150,100,false,true,false);
  obstacles[9][6]= new obstacle(width/2-50,0,width/2+50,100,false,true,true);
  enemies[0][0]=new enemy(10,100,500,1000,5,30,"melee",true,10);//damage, health, xpos, ypos, movement, size, behavior, inplay, loot
  enemies[0][1]=new enemy(10,100,500,900,5,30,"melee",false,10);
  enemies[0][2]=new enemy(10,100,600,1000,5,30,"melee",false,10);
  enemies[0][3]=new enemy(10,100,500,700,5,30,"melee",false,10);
  enemies[1][0]=new enemy(15,250,width/2,height/2,3,100,"boss1",true,100);
  enemies[1][1]=new enemy(10,100,width/6,height/2,6,30,"melee",false,10);
  enemies[1][2]=new enemy(10,100,width*1/2,height/2,6,30,"melee",false,10);
  enemies[1][3]=new enemy(10,100,width*5/6,height/2,6,30,"melee",false,10);
  enemies[2][0]=new enemy(10,100,1700,1000,5,30,"jumping",true,25);
  enemies[2][1]=new enemy(10,100,400,500,5,30,"limit melee",true,40);
  enemies[2][2]=new enemy(10,100,500,100,5,30,"jumping",true,25);
  enemies[2][3]=new enemy(10,0,100,100,6,3,"limit melee",false,15);
  enemies[3][0]=new enemy(10,150,width/2,height/2,6,45,"sword",true,30);
  enemies[3][1]=new enemy(10,150,width/2+100,height/2,5,45,"sword",false,25);
  enemies[3][2]=new enemy(10,150,width/2-100,height/2,5,45,"sword",false,25);
  enemies[3][3]=new enemy(10,0,width/2-100,height/2,5,60,"sword",false,20);
  enemies[4][0]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][1]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][2]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[5][0]=new enemy(15,1200,width/2,height/2,5,100,"boss2",true,100);
  enemies[5][1]=new enemy(20,50,width/2-100,height/2,4,30,"melee",false,20);
  enemies[5][2]=new enemy(20,50,width/2,height/2,4,30,"melee",false,20);
  enemies[5][3]=new enemy(20,50,width/2+100,height/2,4,30,"melee",false,20);
  enemies[6][0]=new enemy(10,150,width/2-100,height/2,5,30,"jumping",true,30);
  enemies[6][1]=new enemy(10,150,width/2+100,height/2,4,30,"jumping",true,30);
  enemies[6][2]=new enemy(10,250,width/2,height/2-100,5,45,"sword",true,30);
  enemies[6][3]=new enemy(10,150,width/2,height/2+100,4,30,"jumping",true,30);
  enemies[7][0]=new enemy(10,250,width/2-200,height/2+200,5,30,"jumping",true,30);
  enemies[7][1]=new enemy(10,250,width/2+200,height/2+200,4,30,"jumping",true,30);
  enemies[7][2]=new enemy(10,250,width/2+200,height/2-200,4,30,"jumping",true,30);
  enemies[7][3]=new enemy(10,250,width/2-200,height/2-200,4,30,"jumping",true,30);
  enemies[8][0]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][1]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][2]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[9][0]=new enemy(20,1000,width/2,height/2,5,100,"boss3",true,20);
  enemies[9][1]=new enemy(15,400,width/2,height/2,5,60,"rider",false,20);
  enemies[9][2]=new enemy(15,600,width/2-100,height/2,5,100,"dragon",false,20);
  enemies[9][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  //position,type,inplay,used
  powerups[0][0]= new powerup(0,0,"",false,true,0);//level one doesnt get a powerup
  powerups[1][0]= new powerup(width/2,height/2,"health_canister",false,false,0);
  powerups[2][0]= new powerup(500,600,"reach",true,false,0);
  powerups[3][0]= new powerup(width/2,height/2,"damage",false,false,0);
  powerups[4][0]= new powerup(0,0,"",false,true,0);
  powerups[5][0]= new powerup(0,0,"",false,true,0);
  powerups[6][0]= new powerup(width/2,height/2,"damage",false,false,0);
  powerups[7][0]= new powerup(0,0,"",false,true,0);
  powerups[8][0]= new powerup(0,0,"",false,true,0);
  powerups[9][0]= new powerup(0,0,"",false,true,0);
  //level five and level 9 will be shops
  powerupshop[0]= new powerup(width/2,height/2,"health_canister",true,false,175);
  powerupshop[1]= new powerup(width/2-200,height/2,"damage",true,false,125);
  powerupshop[2]= new powerup(width/2+200,height/2,"movement",true,false,150);
  powerupshop2[0]= new powerup(width/2,height/2,"health_canister",true,false,175);
  powerupshop2[1]= new powerup(width/2-200,height/2,"reach",true,false,150);
  powerupshop2[2]= new powerup(width/2+200,height/2,"movement",true,false,150);
  paused=true;
  buttons[0]= new button(width/2-50.f,height*3/4-50.f,width/2+50.f,height*3/4+50.f,"Restart?","reset",false);
  buttons[1]=new  button(width/2-50.f,height*1/2-50.f,width/2+50.f,height*1/2+50.f,"Start?","start",true);
  level=-1;
  won=false;
  frames=0;
}
public void draw(){
  
  background(220);
  
  if(won){
    paused=true;
    background(255);
      fill(0);
      textSize(200);
      text("VICTORY",width/2,height/2);
      buttons[0].visib=true;
  }
  else if(paused&&level>-1&&player1.health>0){
    
    fill(0);
    textSize(200);
    text("PAUSED",width/2,height/2);
    textSize(100);
    text("Press P to unpause", width/2, height/2+100);
  }
  if(buttons[1].visib){
    fill(0);
    textSize(100);
    text("SWORDSMAN QUEST",width/2,height/4);
  }
  if(!paused&&level<=levelCap){
    frames+=1;
    imageMode(CORNERS);
    image(background[level],0,0,1920,1080);
  if(frames>7200*(level+1)&&level!=9&&level!=5){
    if(level==1||level==8||level==6){
    obstacles[level][5].invis=false;
    }
    else if(level==0){
      obstacles[level][4].invis=false;
    }
    else if(level==2){
      obstacles[level][1].invis=false;
    }
    else{
      obstacles[level][6].invis=false;
    }
  }
  for(int i=0;i<enemies[level].length;i++){
    enemies[level][i].update();
  }
  for(int i=0; i<obstacles[level].length;i++){
    obstacles[level][i].update();
   
  }
  for(int i=0;i<enemies[level].length;i++){
    enemies[level][i].move();
  }
  for(int i=0; i<powerups[level].length;i++){
    powerups[level][i].update();
    if(level==4){
      for(int j=0; j<powerupshop.length;j++)
      {
      powerupshop[j].update();
      }
    }
    if(level==8){
      for(int k=0; k<powerupshop2.length;k++)
      {
      powerupshop2[k].update();
      }
      }
    
  }
  
  player1.move();
  player1.update();
  fill(255);
  textSize(40);
  text("Level "+(level+1),width-200,200);
  text(player1.money+" coins",width-200,300);
  }

  
 
  
  
  for(int i=0;i<buttons.length;i++){
    buttons[i].update();
    
  }
  if(level==0&&!paused){
    fill(255);
    textSize(30);
    text("Arrow Keys or WASD to Move",400,100);
    text("Click to attack, use cursor to aim, a mouse is recommended",600,300);
    text("Flashing shows immunity",400,700);
    text("Enemy was here",500,1000);
    text("This is a spiked wall",1100,600);
    text("This isn't a spiked wall",1500,600);
    text("Door will spawn here", width/2,150);
    text("Press P to pause",400,500);
    if(enemies[0][0].alive==false&& enemies[0][1].inplay==false){
      enemies[0][1].inplay=true;
      enemies[0][2].inplay=true;
      enemies[0][3].inplay=true;
    
    }
  }
  if(level==3){
    if(enemies[3][0].alive==false&&enemies[3][1].inplay==false){
      enemies[3][1].inplay=true;
      enemies[3][2].inplay=true;
    }

  }
  if(level==9&&!paused){
    if(!enemies[9][0].alive&&!enemies[9][1].inplay){
      enemies[9][2].xpos=enemies[9][0].xpos;
      enemies[9][2].ypos=enemies[9][0].ypos;
      enemies[9][1].inplay=true;
      enemies[9][2].inplay=true;
    }
    }
  
  
  
}

public void keyReleased(){
  if(keyCode==UP||keyCode==DOWN||key=='s'||key=='S'||key=='W'||key== 'w'){
    player1.ymov=0;
  }
  if(keyCode==LEFT||keyCode==RIGHT||key=='a'||key=='A'||key=='d'||key=='D'){
    player1.xmov=0;
  }
}
public void keyPressed(){
  if(key== 'w'||keyCode==UP||key=='W'){
      player1.ymov=-1;
    }
    else if(keyCode== DOWN||key=='s'||key=='S'){
       player1.ymov=1;
    }
    else if(keyCode== LEFT||key=='a'||key=='A'){
       player1.xmov=-1;
    }
    else if(keyCode== RIGHT||key=='d'||key=='D'){
       player1.xmov=1;
    }
    if(key=='p'||key=='P'&&level>-1){
      paused=!paused;
    }
}
public void mousePressed(){
  player1.attack();
  
}
public class button {
  float leftx,topy,rightx,bottomy;
  String text, type;
  boolean visib;
  button(float lx, float ty, float rx, float by, String te, String typ, boolean vis){
    leftx=lx;
    topy=ty;
    rightx=rx;
    bottomy=by;
    text=te;
    type=typ;
    visib=vis;
  }
  public void update(){
    noStroke();
    if(visib){
    fill(0,0,255);
    }
    else{
      noFill();
    }
    if(leftx<mouseX&&mouseX<rightx&&topy<mouseY&&mouseY<bottomy&&visib){
      
      if(mousePressed){
      if(type=="reset"){
        setup();
        visib=false;
      }
      if(type=="start"){
        level=0;
        visib=false;
        paused=false;
      }
      }
      fill(220);
    }
    
    rect(leftx,topy,rightx,bottomy);
    if(visib){
    fill(0,255,0);
    textAlign(CENTER);
    textSize(20);
    text(text,(leftx+rightx)/2,(topy+bottomy)/2);
    }
    
  }
}
public class entity {
  public int damage,health,facingx,facingy,immunity,maxHealth,money,attackCooldown;//money refers to the loot system
  public float xpos,ypos,movs,size,xmov,ymov,invincFrames;
  public void move(){
    if(xpos-size/2<0&&xmov<0){
      xmov=0;
    }
    if(ypos-size/2<0&&ymov<0){
      ymov=0;
    }
    if(xpos+size/2>width&&xmov>0){
      xmov=0;
    }
    if(ypos+size/2>height&&ymov>0){
      ymov=0;
    }
    xpos+=movs*xmov;
    ypos+=movs*ymov;
    if(immunity>0){
      immunity-=1;
    }
    
    
  }
  public void hit(int dam){
    if(immunity==0){
    health-=dam;
    immunity+=invincFrames;
  }
  }
  
}
public class player extends entity{
  
  
   int attackSpeed,reach;
  player(int h,float x ,float y,float m){
    health=h;
    xpos=x;
    ypos=y;
    movs=m;
    xmov=0;
    ymov=0;
    size=60;
    damage=20;
    facingx=1;
    facingy=0;
    immunity=0;
    invincFrames=30;
    attackCooldown=0;
    attackSpeed=15;
    reach=100;
    maxHealth=h;
    money=0;
  }
  public void update(){
    imageMode(CENTER);
    
    if(facingy==1&&attackCooldown>0){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerTop,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerTop1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerTop2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerTop3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerTop4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerTop5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerTopHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else{
      image(playerTop,xpos,ypos,size*1.5f,size*2);
    }
    }
    else if(facingy==-1&&attackCooldown>0){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerBottom,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerBottom1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerBottom2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerBottom3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerBottom4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerBottom5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerBottomHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else{
      image(playerBottom,xpos,ypos,size*1.5f,size*2);
    }
    }
    else if(facingx==1&&attackCooldown>0){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=3){
        image(playerRight,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerRight1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerRight2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerRight3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerRight4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerRightHurt,xpos,ypos,size,size*2);
      }
    }
    else{
      image(playerRight,xpos,ypos,size,size*2);
    }
    }
    else if(facingx==-1&&attackCooldown>0){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=2){
        image(playerLeft,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerLeft1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerLeft2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerLeft3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerLeft4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerLeftHurt,xpos,ypos,size,size*2);
      }
    }
    else{
      image(playerLeft,xpos,ypos,size,size*2);
    }
    }
    else if(ymov==1){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerTop,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerTop1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerTop2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerTop3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerTop4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerTop5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerTopHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else if(ymov==-1){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerBottom,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerBottom1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerBottom2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerBottom3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerBottom4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerBottom5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerBottomHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else if(xmov==1){
      
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=3){
        image(playerRight,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerRight1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerRight2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerRight3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerRight4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerRightHurt,xpos,ypos,size,size*2);
      }
    }
 
    else if(xmov==-1){
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=2){
        image(playerLeft,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerLeft1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerLeft2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerLeft3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerLeft4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerLeftHurt,xpos,ypos,size,size*2);
      }
    }
    else if(facingy==1){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerTop,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerTop1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerTop2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerTop3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerTop4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerTop5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerTopHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else{
      image(playerTop,xpos,ypos,size*1.5f,size*2);
    }
    }
    else if(facingy==-1){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 18 >=0&&frames % 18<=2){
        image(playerBottom,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=3&&frames % 18<=5){
        image(playerBottom1,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=6&&frames % 18<=8){
        image(playerBottom2,xpos,ypos,size*1.5f,size*2);
        
      }
      if(frames % 18 >=9&&frames % 18<=11){
        image(playerBottom3,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=12&&frames % 18<=14){
        image(playerBottom4,xpos,ypos,size*1.5f,size*2);
       
      }
      if(frames % 18 >=15&&frames % 18<=17){
        image(playerBottom5,xpos,ypos,size*1.5f,size*2);
       
      }
      
     
    }
    else{
        image(playerBottomHurt,xpos,ypos,size*1.5f,size*2);
      }
    }
    else{
      image(playerBottom,xpos,ypos,size*1.5f,size*2);
    }
    }
    else if(facingx==1){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=3){
        image(playerRight,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerRight1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerRight2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerRight3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerRight4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerRightHurt,xpos,ypos,size,size*2);
      }
    }
    else{
      image(playerRight,xpos,ypos,size,size*2);
    }
    }
    else if(facingx==-1){
      if(xmov!=0||ymov!=0){
      if(immunity==0){
      if(frames % 15 >=0&&frames % 15<=2){
        image(playerLeft,xpos,ypos,size,size*2);
        
      }
      if(frames % 15 >=3&&frames % 15<=5){
        image(playerLeft1,xpos,ypos,size*23/16,size*2);
        
      }
      if(frames % 15 >=6&&frames % 15<=8){
        image(playerLeft2,xpos,ypos,size*35/16,size*2);
        
      }
      if(frames % 15 >=9&&frames % 15<=11){
        image(playerLeft3,xpos,ypos,size*18/16,size*2);
       
      }
      if(frames % 15 >=12&&frames % 15<=14){
        image(playerLeft4,xpos,ypos,size*32/16,size*2);
       
      }
      
      
     
    }
    else{
        image(playerLeftHurt,xpos,ypos,size,size*2);
      }
    }
    else{
      image(playerLeft,xpos,ypos,size,size*2);
    }
    }
    if(attackCooldown!=0)
    {
      imageMode(CORNER);
      if(facingy==1){
          
          if(reach==100){
            image(shortsword_back,xpos,ypos,20,reach);
            }
            else if(reach==150){
              image(longsword_back,xpos,ypos,20,reach);
            }
            else{
              image(sword_back,xpos,ypos,20,reach);
            }
          
        }
        if(facingy==-1){
          
          if(reach==100){
            image(img_shortsword,xpos,ypos,20,-reach);
            }
            else if(reach==150){
              image(img_longsword,xpos,ypos,20,-reach);
            }
            else{
              image(sword,xpos,ypos,20,-reach);
            }
        }
        if(facingx==1){
          
          if(reach==100){
            image(img_shortsword_rotate,xpos,ypos,reach,20);
            }
            else if(reach==150){
              image(img_longsword_rotate,xpos,ypos,reach,20);
            }
            else{
              image(sword_rotate,xpos,ypos,reach,20);
            }
        }
        if(facingx==-1){
          
          if(reach==100){
            image(shortsword_left,xpos,ypos,-reach,20);
            }
            else if(reach==150){
              image(longsword_left,xpos,ypos,-reach,20);
            }
            else{
              image(sword_left,xpos,ypos,-reach,20);
            }
        }
    }
    
    stroke(0);
    strokeWeight(3);
    rectMode(CORNERS);
    fill(255);
    rect(0,0,200,50);
    fill(255,0,0);
    
    rect(0,0,health*200/maxHealth,50);
    fill(0);
    textSize(25);
    textAlign(CENTER);
    text(health+"/"+maxHealth,100,25);
    //health bar chunk ^^^
    fill(0);
    noStroke();
    
    imageMode(CENTER);
    
    rectMode(CORNERS);
    if(health<=0){
      background(0);
      fill(255,0,0);
      textSize(200);
      text("Game Over",width/2,height/2);
      buttons[0].visib=true;
      
    }
      if(attackCooldown>0){
      attackCooldown-=1;
      }
  }
  public void attack(){
    if(abs(mouseX-xpos)<abs(mouseY-ypos)){
      if(mouseY-ypos>0){
        facingy=1;
        facingx=0;
        
        
      }
      else{
        facingy=-1;
        facingx=0;
      }
    }
    else{
      if(mouseX-xpos>0){
        facingx=1;
        facingy=0;
      }
      else{
        facingx=-1;
        facingy=0;
      }
    }
    if(attackCooldown==0&&!paused){
   
    attackCooldown+=attackSpeed;
    noStroke();
    imageMode(CORNER);
    if(reach!=200){
    if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
    }
    else{
      if(random(0,1)>0.7f){
        longSwordStrike.play();
      }
      else{
        extraSwordStrike.play();
      }
    }
      for(enemy example:enemies[level]){
        
        if(example.inplay){
        if(facingy==1){
          if( abs(example.xpos-xpos)<example.size/2&&example.ypos>ypos+size/2&&ypos+facingy*(size/2+reach)>example.ypos-example.size/2){
            example.hit(damage);
            if(example.behavior=="sword"||example.behavior=="rider"){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            if(example.behavior=="melee"||example.behavior=="boss1"||example.behavior=="limit melee"){
              hurtBat.play();
          }
          if(example.behavior=="jumping"){
            hurtSpider.play();
          }
          if(example.behavior=="boss2"){
            if(random(0,1)>0.7f){
              hurtGolem1.play();
            }
            else{
              hurtGolem2.play();
            }
          }
          if(example.behavior=="boss3"||example.behavior=="dragon"){
            hurtDragon.play();
          }
        
            }
            if(reach==100){
            image(shortsword_back,xpos,ypos,20,reach);
           
            }
            else if(reach==150){
              image(longsword_back,xpos,ypos,20,reach);
              
            }
            else{
              image(sword_back,xpos,ypos,20,reach);
              
        }
        }
        if(facingy==-1){
          if( abs(example.xpos-xpos)<example.size/2&&example.ypos<ypos-size/2&&ypos+facingy*(size/2+reach)<example.ypos+example.size/2){
            example.hit(damage);
            if(example.behavior=="sword"||example.behavior=="rider"){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            if(example.behavior=="melee"||example.behavior=="boss1"||example.behavior=="limit melee"){
              hurtBat.play();
          }
          if(example.behavior=="jumping"){
            hurtSpider.play();
          }
          if(example.behavior=="boss2"){
            if(random(0,1)>0.7f){
              hurtGolem1.play();
            }
            else{
              hurtGolem2.play();
            }
          }
          if(example.behavior=="boss3"||example.behavior=="dragon"){
            hurtDragon.play();
          }
          }
          
          if(reach==100){
            image(img_shortsword,xpos,ypos,20,-reach);
            
            }
            else if(reach==150){
              image(img_longsword,xpos,ypos,20,-reach);
              
            }
            else{
              image(sword,xpos,ypos,20,-reach);
              
            }
        }
        if(facingx==1){
          if( abs(example.ypos-ypos)<example.size/2&&example.xpos>xpos+size/2&&xpos+facingx*(size/2+reach)>example.xpos-example.size/2){
            example.hit(damage);
            if(example.behavior=="sword"||example.behavior=="rider"){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            if(example.behavior=="melee"||example.behavior=="boss1"||example.behavior=="limit melee"){
              hurtBat.play();
          }
          if(example.behavior=="jumping"){
            hurtSpider.play();
          }
          if(example.behavior=="boss2"){
            if(random(0,1)>0.7f){
              hurtGolem1.play();
            }
            else{
              hurtGolem2.play();
            }
          }
          if(example.behavior=="boss3"||example.behavior=="dragon"){
            hurtDragon.play();
          }
          }
          if(reach==100){
            image(img_shortsword_rotate,xpos,ypos,reach,20);
            
            }
            else if(reach==150){
              image(img_longsword_rotate,xpos,ypos,reach,20);
              
            }
            else{
              image(sword_rotate,xpos,ypos,reach,20);
              
            }
        }
        if(facingx==-1){
          if( abs(example.ypos-ypos)<example.size/2&&example.xpos<xpos-size/2&&xpos+facingx*(size/2+reach)<example.xpos+example.size/2){
            example.hit(damage);
            if(example.behavior=="sword"||example.behavior=="rider"){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
           if(example.behavior=="melee"||example.behavior=="boss1"||example.behavior=="limit melee"){
              hurtBat.play();
          }
          if(example.behavior=="jumping"){
            hurtSpider.play();
          }
          if(example.behavior=="boss2"){
            if(random(0,1)>0.7f){
              hurtGolem1.play();
            }
            else{
              hurtGolem2.play();
            }
          }
          if(example.behavior=="boss3"||example.behavior=="dragon"){
            hurtDragon.play();
          }
          }
          if(reach==100){
            image(shortsword_left,xpos,ypos,-reach,20);
            
            }
            else if(reach==150){
              image(longsword_left,xpos,ypos,-reach,20);
              
            }
            else{
              image(sword_left,xpos,ypos,-reach,20);
              
            }
        }
      }
    }
    
}
}
}



public class obstacle { 
  float leftx,topy,rightx,bottomy;
  boolean spiked,invis,door;
  obstacle(float lx, float ty, float rx, float by, boolean s,boolean inv,boolean dor){
    leftx=lx;//visually left
    topy=ty;//the one visually on top
    rightx=rx;
    bottomy=by;
    spiked=s;
    invis=inv;
    door=dor;
    
  }
public void update(){
  if(door&&invis){
  int enemiesAlive=0;
  for(int i=0; i<enemies[level].length;i++){
  if(enemies[level][i].alive){
    enemiesAlive+=1;
  }
  
  }
  if(enemiesAlive==0){
    invis=false;
    for(int j=0; j<powerups[level].length;j++){
    powerups[level][j].inplay=true;
    }
  }
  }  
    
    
    noStroke();
    
    if(!invis){
      
      imageMode(CORNERS);
      if(spiked){
       if(level==0){
      image(spikedWall,leftx,topy,rightx,bottomy);
       }
       else{
         if(rightx-leftx>bottomy-topy){
           image(spikedWallHor,leftx,topy,rightx,bottomy);
         }
         else{
           image(spikedWallVer,leftx,topy,rightx,bottomy);
         }
       }
    }
    if(!door&&!spiked){
      if(level==0){
      image(normal,leftx,topy,rightx,bottomy);
       }
       else{
         if(rightx-leftx>bottomy-topy){
           image(normalHor,leftx,topy,rightx,bottomy);
         }
         else{
           image(normalVer,leftx,topy,rightx,bottomy);
         }
       }
    }
    
    if(door){
      if(level!=2&&level!=levelCap){
      image(caveDoor,leftx,topy,rightx,bottomy);
      }
      else if(level==2){
       image(caveDoorLeft,leftx,topy,rightx,bottomy);
      }
      else{
        image(lightDoor,leftx,topy,rightx,bottomy);
      }
    }
    for(entity example:enemies[level]){
     collision(example);
    }
    collision(player1);
  }
}
  public void collision(entity example){
     if(example.xpos-example.size/2<=rightx && example.xpos-example.size/2>=rightx-example.movs  && example.ypos+example.size/2 >= topy && example.ypos - example.size/2 <=bottomy){//right side collision
      if(example.xmov<0){
      example.xmov=0;
      }
      if(spiked&&example.immunity==0){
      example.hit(10);
      }
      if(door&&example==player1&&!invis){
        if(level<levelCap){
        level+=1;
        }
        else{
          won=true;
        }
        if(example.xpos>width/2){
        example.xpos=100;
        }
        else{
          example.xpos=width-100;
        }
      }
    }
    if(example.ypos-example.size/2<=bottomy && bottomy-example.movs <=example.ypos-example.size/2  && example.xpos + example.size/2 >=leftx && example.xpos-example.size/2 <= rightx ){//bottom side collision
      if(example.ymov<0){
      example.ymov=0;
      }
      if(spiked&&example.immunity==0){
      example.hit(10);
      }
     if(door&&example==player1&&!invis){
        if(level<levelCap){
        level+=1;
        }
        else{
          won=true;
        }
        example.ypos=height-100;
      }
    }
    if(leftx+example.movs>=example.xpos+example.size/2 && example.xpos+example.size/2 >= leftx && example.ypos+example.size/2 >= topy && example.ypos - example.size/2<=bottomy){//left side collision
      if(example.xmov>0){
      example.xmov=0;
      }
      if(spiked&&example.immunity==0){
      example.hit(10);
      }
      if(door&&example==player1&&!invis){
        if(level<levelCap){
        level+=1;
        }
        else{
          won=true;
        }
        example.xpos=width-100;
      }
    }
    if(example.ypos+example.size/2>=topy&& example.xpos + example.size/2 >= leftx && example.xpos-example.size/2 <= rightx && example.ypos+example.size/2 <= topy+example.movs){//top side collision
      if(example.ymov>0){
      example.ymov=0;
      }
      if(spiked&&example.immunity==0){
      example.hit(10);
      }
      if(door&&example==player1&&!invis){
        if(level<levelCap){
        level+=1;
        }
        else{
          won=true;
        }
        example.ypos=100;
      }
    }
    
  }
  
}

public class enemy extends entity{
  String behavior, attackPattern;
  boolean alive,inplay;
  float direction,direction2,direction3;
  int attackCooldown2;
  enemy(int d, int h, float xpo, float ypo, float mov, float siz, String b, boolean inpla,int m){
    damage=d;
    xmov=0;
    ymov=0;
    health=h;
    facingx=1;
    facingy=0;
    xpos=xpo;
    ypos=ypo;
    movs=mov;
    size=siz;
    behavior=b;
    immunity=0;
    invincFrames= frameRate*0.5f;
    alive=true;
    inplay=inpla;
    maxHealth=h;
    money=m;
    attackCooldown=0;
    attackCooldown2=0; //solely for the final boss
    attackPattern="";
    direction=0;//for sword enemies and boss enemies
    direction2=0;//dont want this to be x and y since just direction can be used
    direction3=0;
  }
  public void update(){
    if(alive&&inplay){
   
    if(behavior=="melee"){
      imageMode(CENTER);
      
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(meleeEnemyLow,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(meleeEnemyHigh,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(enemyHurt,xpos,ypos,size*2,size*2);
      }
      xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
      ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      if(collision(player1)){
        player1.hit(damage);
      }
    }
    if(behavior=="boss1"){//referring to the first boss, whose behavior is quite simple
    imageMode(CENTER);
      
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(meleeEnemyLow,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(meleeEnemyHigh,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(enemyHurt,xpos,ypos,size*2,size*2);
      }
       xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
       ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      if(collision(player1)){
        player1.hit(damage);
      }
       if(maxHealth*0.5f>health&&level==1&&!enemies[1][2].inplay){
         enemies[1][1].inplay=true;
         enemies[1][2].inplay=true;
         enemies[1][3].inplay=true;
       }
     }
     if(behavior=="limit melee"){//enemies will only move within a certain radius of the player
     imageMode(CENTER);
      
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(meleeEnemyLow,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(meleeEnemyHigh,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(meleeEnemy,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(enemyHurt,xpos,ypos,size*2,size*2);
      }
      if((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos)<200*200){
      xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(xpos-player1.xpos));
      ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(ypos-player1.ypos));
      }
      else{
        xmov=0;
        ymov=0;
      }
      
      if(collision(player1)){
        player1.hit(damage);
      }
    }
    if(behavior=="sword"){
      imageMode(CENTER);
      
      ////image(sword_enemy,xpos,ypos,size,size);
      
       if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(swordEnemyLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(swordEnemy,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(swordEnemyRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(swordEnemy,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(swordEnemyHurt,xpos,ypos,size*2,size*2);
        

      }
      imageMode(CORNER);
      if(attackCooldown>0){//this use of negative and positive attackCooldown is useful
        attackCooldown-=1;
        
            if(direction>0){
            image(shortsword_left,xpos,ypos,-100,20);
            
            }
            else{
              image(img_shortsword_rotate,xpos,ypos,100,20);
            }
      }
      else if(attackCooldown<0){
        attackCooldown+=1;
       
        if(direction<0){
            image(shortsword_back,xpos,ypos,20,100);
            
            }
            else{
              image(img_shortsword,xpos,ypos,20,-100);
            }
      }
      imageMode(CORNER);
        if(abs(xpos-player1.xpos)>abs(ypos-player1.ypos)){
          xmov= -(xpos-player1.xpos)*(abs(xpos-player1.xpos)-80)*(abs(xpos-player1.xpos)-80)/(((abs(xpos-player1.xpos)-80)*(abs(xpos-player1.xpos)-80)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(xpos-player1.xpos));
          ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(ypos-player1.ypos));
          if(abs(xpos-player1.xpos)<100&&abs(ypos-player1.ypos)<player1.size&&attackCooldown==0){
            
            
            direction=(xpos-player1.xpos)/abs(xpos-player1.xpos);
            
            if(direction>0){
            image(shortsword_left,xpos,ypos,-100,20);
            
            }
            else{
              image(img_shortsword_rotate,xpos,ypos,100,20);
            }
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
           swordHurt1.play();
           }
           else{
          swordHurt2.play();
        }
            }
            player1.hit(damage);
            
            attackCooldown+=frameRate*0.5f;
          }
        }
        else{
           ymov= -(ypos-player1.ypos)*(abs(ypos-player1.ypos)-80)*(abs(ypos-player1.ypos)-80)/(((abs(ypos-player1.ypos)-80)*(abs(ypos-player1.ypos)-80)+(xpos-player1.xpos)*(xpos-player1.xpos))*abs(ypos-player1.ypos));
            xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(xpos-player1.xpos));
            if(abs(ypos-player1.ypos)<100&&abs(xpos-player1.xpos)<player1.size&&attackCooldown==0){
              strokeWeight(5);
            
            direction=(ypos-player1.ypos)/abs(ypos-player1.ypos);
            if(direction<0){
            image(shortsword_back,xpos,ypos,20,100);
            
            }
            else{
              image(img_shortsword,xpos,ypos,20,-100);
            }
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            player1.hit(damage);
            
            attackCooldown-=frameRate*0.5f;
          }
        }
      
      
    }
    if(behavior=="boss2"){//second bossfight
    imageMode(CENTER);
    
    if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(golemLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(golem,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(golemRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(golem,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(golemHurt,xpos,ypos,size*2,size*2);
      }
      if(attackCooldown==0){
        float i=random(0,2);
        if(0<=i&&i<=1){
          attackCooldown=720;
        }
        else{
          attackCooldown=-300;
        }
      }
      else{
        if(attackCooldown>0){
          attackCooldown-=1;
        }
        else if(attackCooldown<0){
          attackCooldown+=1;
        }
      }
      if(attackCooldown<=720&&attackCooldown>=300){
        xmov=0;
        ymov=0;
        strokeWeight(1);
        stroke(255,0,0);
        line(player1.xpos,player1.ypos,xpos,ypos);
        
      }
      if(attackCooldown<300&&attackCooldown>0){
        xmov=2.5f*-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        ymov=2.5f*-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(ypos-player1.ypos));
       
      }
      if(attackCooldown>=-300&&attackCooldown<0){
        ellipseMode(CENTER);
        if(attackCooldown<=-60){
          fill(255,0,0,64);
          ellipse(xpos,ypos,400,400);
           xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
           ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
        }
        if(attackCooldown>=-60){
          fill(255,0,0,192);
          ellipse(xpos,ypos,400*(60+attackCooldown)/60,400*(60+attackCooldown)/60);
          if((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos)<abs(200*(60+attackCooldown)/60)*200*abs(60+attackCooldown)/60){
            player1.hit(damage);
            xmov=0;
            ymov=0;
          }
          
        }
      }
      if(health<maxHealth/2&&enemies[5][1].inplay==false){
        
        enemies[5][1].inplay=true;
        enemies[5][2].inplay=true;
        enemies[5][3].inplay=true;
      }
      if(collision(player1)){
        player1.hit(damage);
      }
    }
    if(behavior=="jumping"){
      imageMode(CENTER);
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(spiderLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(spider,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(spiderRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(spider,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(spiderHurt,xpos,ypos,size*2,size*2);
      }
      if(attackCooldown==0){
      if((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos)<22500){
        ypos=player1.ypos+random(-30,30);
        xpos=player1.xpos+random(-30,30);
      }
      else{
        if(abs(xpos-player1.xpos)>abs(ypos-player1.ypos)){
          if(xpos-player1.xpos<0){
            xpos+=150;
          }//left
          else{
            xpos-=150;
          }//right
        }
        else{
          if(ypos-player1.ypos<0){
            ypos+=150;
          }//up
          else{
            ypos-=150;
          }//down
        }
      }
      attackCooldown+=180;
      }
      else{
        attackCooldown-=1;
      }
      if(collision(player1)){
        player1.hit(damage);
      }
      
    }
    if(behavior=="boss3"){
      imageMode(CENTER);
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(bossLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(boss,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(bossRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(boss,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(bossHurt,xpos,ypos,size*2,size*2);
      }
      if(attackCooldown==0){
        
        float r= random(0,2);
        if(1>=r){
          attackCooldown+=360;
        }
        else{
          attackCooldown-=300;
        }
      }
      else if(attackCooldown>0){
        attackCooldown-=1;
      }
      else if(attackCooldown<0){
        attackCooldown+=1;
      }
      if(attackCooldown<=360&&attackCooldown>0&&attackCooldown%120==0){
        fireballSound.play();
        direction=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        direction2=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(attackCooldown<=360&&attackCooldown>0){
        xmov=0.2f*(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        ymov=0.2f*(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(attackCooldown<=360&&attackCooldown>240){
        imageMode(CENTER);
        
        image(fireball,xpos+direction*2*movs*(360-attackCooldown),ypos+direction2*2*movs*(360-attackCooldown),100,100);
        
        if((xpos+direction*2*movs*(360-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(360-attackCooldown)-player1.xpos)+(ypos+direction2*movs*2*(360-attackCooldown)-player1.ypos)*(ypos+direction2*movs*2*(360-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<=240&&attackCooldown>120){
        imageMode(CENTER);
        image(fireball,xpos+direction*2*movs*(240-attackCooldown),ypos+direction2*2*movs*(240-attackCooldown),100,100);
       
        if((xpos+direction*2*movs*(240-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(240-attackCooldown)-player1.xpos)+(ypos+direction2*movs*2*(240-attackCooldown)-player1.ypos)*(ypos+direction2*movs*2*(240-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<=120&&attackCooldown>0){
        imageMode(CENTER);
        image(fireball,xpos+direction*2*movs*(120-attackCooldown),ypos+direction2*2*movs*(120-attackCooldown),100,100);
        
        if((xpos+direction*2*movs*(120-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(120-attackCooldown)-player1.xpos)+(ypos+direction2*2*movs*(120-attackCooldown)-player1.ypos)*(ypos+direction2*2*movs*(120-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<0&&attackCooldown>=-300){
        xmov=1.4f*-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        ymov=1.4f*-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(attackCooldown2==0){
        imageMode(CORNER);
        if(abs(xpos-player1.xpos)<150&&abs(ypos-player1.ypos)<player1.size){
            direction3=(xpos-player1.xpos)/abs(xpos-player1.xpos);
            
            if(direction3>0){
            image(trident_left,xpos,ypos,-150,20);
            
            }
            else{
              image(trident_rotate,xpos,ypos,150,20);
            }
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            player1.hit(damage);
            
            attackCooldown2-=60;
          }
         
          else if(abs(ypos-player1.ypos)<150&&abs(xpos-player1.xpos)<player1.size){
              
               imageMode(CORNER);
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
            direction3=(ypos-player1.ypos)/abs(ypos-player1.ypos);
            if(direction3<0){
            image(trident_back,xpos,ypos,20,150);
            
            }
            else{
              image(trident,xpos,ypos,20,-150);
            }
            player1.hit(damage);
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
         else{
          swordHurt2.play();
        }
            }
            attackCooldown2+=60;
          }
      }
      if(attackCooldown2>0){
        imageMode(CORNER);
        
        attackCooldown2-=1;
        if(direction3<0){
            image(trident_back,xpos,ypos,20,150);
            
            }
            else{
              image(trident,xpos,ypos,20,-150);
            }
        
      }
      else if(attackCooldown2<0){
        imageMode(CORNER);
        attackCooldown2+=1;
        if(direction3>0){
            image(trident_left,xpos,ypos,-150,20);
            
            }
            else{
              image(trident_rotate,xpos,ypos,150,20);
            }
      }
      if(collision(player1)){
        player1.hit(damage/2);
      }
    }
    if(behavior=="rider"){
      imageMode(CENTER);
      
      ////image(sword_enemy,xpos,ypos,size,size);
      
       if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(riderLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(rider,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(riderRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(rider,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(riderHurt,xpos,ypos,size*2,size*2);
      }
      imageMode(CORNER);
      if(attackCooldown>0){
        attackCooldown-=1;
        
            if(direction>0){
            image(trident_left,xpos,ypos,-150,20);
            
            }
            else{
              image(trident_rotate,xpos,ypos,100,20);
            }
      }
      else if(attackCooldown<0){
        attackCooldown+=1;
       
        if(direction<0){
            image(trident_back,xpos,ypos,20,100);
            
            }
            else{
              image(trident,xpos,ypos,20,-100);
            }
      }
      imageMode(CORNER);
         if(abs(xpos-player1.xpos)>abs(ypos-player1.ypos)){
          xmov= -(xpos-player1.xpos)*(abs(xpos-player1.xpos)-135)*(abs(xpos-player1.xpos)-135)/(((abs(xpos-player1.xpos)-135)*(abs(xpos-player1.xpos)-135)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(xpos-player1.xpos));
          ymov=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(ypos-player1.ypos));
          if(abs(xpos-player1.xpos)<100&&abs(ypos-player1.ypos)<player1.size&&attackCooldown==0){
            
            direction=(xpos-player1.xpos)/abs(xpos-player1.xpos);
            
            if(direction>0){
            image(trident_left,xpos,ypos,-150,-20);
            
            }
            else{
              image(trident_rotate,xpos,ypos,150,-20);
            }
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
            swordHurt1.play();
            }
           else{
          swordHurt2.play();
           }
            }
            player1.hit(damage);
            
            attackCooldown+=frameRate;
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
          }
        }
        else{
           ymov= -(ypos-player1.ypos)*(abs(ypos-player1.ypos)-135)*(abs(ypos-player1.ypos)-135)/(((abs(ypos-player1.ypos)-135)*(abs(ypos-player1.ypos)-135)+(xpos-player1.xpos)*(xpos-player1.xpos))*abs(ypos-player1.ypos));
            xmov=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs(xpos-player1.xpos));
            if(abs(ypos-player1.ypos)<150&&abs(xpos-player1.xpos)<player1.size&&attackCooldown==0){
              
            if(random(0,1)>0.5f){
     swordStrike.play();
    }
    else if(random(0,1)>0.5f){
      swordStrike2.play();
    }
    else{
      swordStrike3.play();
    }
            direction=(ypos-player1.ypos)/abs(ypos-player1.ypos);
            if(direction<0){
            image(trident_back,xpos,ypos,-20,150);
            
            }
            else{
              image(trident,xpos,ypos,-20,-150);
            }
            if(player1.immunity==0){
              if(random(0,1)>0.7f){
          swordHurt1.play();
        }
        else{
          swordHurt2.play();
        }
            }
            player1.hit(damage);
            
            attackCooldown-=frameRate;
          }
        }
      
     
    }
    if(behavior=="dragon"){
      imageMode(CENTER);
      if(immunity==0){
      if(frames % 16 >=0&&frames % 16<=3){
        image(dragonLeft,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=4&&frames % 16<=7){
        image(dragon,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=8&&frames % 16<=11){
        image(dragonRight,xpos,ypos,size*2,size*2);
        
      }
      if(frames % 16 >=12&&frames % 16<=15){
        image(dragon,xpos,ypos,size*2,size*2);
       
      }
      
      }
      else{
        image(dragonHurt,xpos,ypos,size*2,size*2);
      }
      if(attackCooldown==0){
        
        float r= random(0,2);
        if(1>=r){
          attackCooldown+=360;
        }
        else{
          attackCooldown-=300;
        }
      }
      else if(attackCooldown>0){
        attackCooldown-=1;
      }
      else if(attackCooldown<0){
        attackCooldown+=1;
      }
      if(attackCooldown<=360&&attackCooldown>0&&attackCooldown%120==0){
        fireballSound.play();
        direction=-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        direction2=-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(attackCooldown<=360&&attackCooldown>0){
        xmov=0.2f*(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        ymov=0.2f*(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(attackCooldown<=360&&attackCooldown>240){
        imageMode(CENTER);
        
        image(fireball,xpos+direction*2*movs*(360-attackCooldown),ypos+direction2*2*movs*(360-attackCooldown),100,100);
        
        if((xpos+direction*2*movs*(360-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(360-attackCooldown)-player1.xpos)+(ypos+direction2*movs*2*(360-attackCooldown)-player1.ypos)*(ypos+direction2*movs*2*(360-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<=240&&attackCooldown>120){
        imageMode(CENTER);
        image(fireball,xpos+direction*2*movs*(240-attackCooldown),ypos+direction2*2*movs*(240-attackCooldown),100,100);
        
        if((xpos+direction*2*movs*(240-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(240-attackCooldown)-player1.xpos)+(ypos+direction2*movs*2*(240-attackCooldown)-player1.ypos)*(ypos+direction2*movs*2*(240-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<=120&&attackCooldown>0){
        imageMode(CENTER);
        image(fireball,xpos+direction*2*movs*(120-attackCooldown),ypos+direction2*2*movs*(120-attackCooldown),100,100);
        
        if((xpos+direction*2*movs*(120-attackCooldown)-player1.xpos)*(xpos+direction*2*movs*(120-attackCooldown)-player1.xpos)+(ypos+direction2*2*movs*(120-attackCooldown)-player1.ypos)*(ypos+direction2*2*movs*(120-attackCooldown)-player1.ypos)<100*100){
          player1.hit(damage);
        }
      }
      if(attackCooldown<0&&attackCooldown>=-300){
        xmov=1.4f*-(xpos-player1.xpos)*(xpos-player1.xpos)*(xpos-player1.xpos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((xpos-player1.xpos)));
        ymov=1.4f*-(ypos-player1.ypos)*(ypos-player1.ypos)*(ypos-player1.ypos)/(((xpos-player1.xpos)*(xpos-player1.xpos)+(ypos-player1.ypos)*(ypos-player1.ypos))*abs((ypos-player1.ypos)));
      }
      if(collision(player1)){
        player1.hit(damage);
      }
    }
    for(int i=0;i<enemies[level].length;i++){
      if(inplay&&alive&&enemies[level][i].alive&&enemies[level][i].inplay){
          if(xpos-enemies[level][i].xpos<(size+enemies[level][i].size)*0.5f&&xpos-enemies[level][i].xpos>0&&abs(ypos-enemies[level][i].ypos)<(size+enemies[level][i].size)*0.5f){
            if(xmov<0){
              xmov=0;
            }
            if(enemies[level][i].xmov>0){
              enemies[level][i].xmov=0;
            }
          }
          if(-xpos+enemies[level][i].xpos<(size+enemies[level][i].size)*0.5f&&-xpos+enemies[level][i].xpos>0&&abs(ypos-enemies[level][i].ypos)<(size+enemies[level][i].size)*0.5f){
            if(xmov>0){
              xmov=0;
            }
            if(enemies[level][i].xmov<0){
              enemies[level][i].xmov=0;
            }
          }
          if(ypos-enemies[level][i].ypos<(size+enemies[level][i].size)*0.5f&&ypos-enemies[level][i].ypos>0&&abs(xpos-enemies[level][i].xpos)<(size+enemies[level][i].size)*0.5f){
            if(ymov<0){
              ymov=0;
            }
            if(enemies[level][i].ymov>0){
              enemies[level][i].ymov=0;
            }
          }
          if(-ypos+enemies[level][i].ypos<(size+enemies[level][i].size)*0.5f&&-ypos+enemies[level][i].ypos>0&&abs(xpos-enemies[level][i].xpos)<(size+enemies[level][i].size)*0.5f){
            if(ymov>0){
              ymov=0;
            }
            if(enemies[level][i].ymov<0){
              enemies[level][i].ymov=0;
            }
          }
        }
    }
  }
  if(health<=0&&alive){
    alive=false;
    if(inplay){
    player1.money +=money;
    }
  }
  }
  public boolean collision(entity entity1){
  if(abs((xpos-entity1.xpos))<(entity1.size+size)/2&&abs((ypos-entity1.ypos))<(entity1.size+size)/2){
      return true;
    }
    else{
    return false;
    }
}
}
class powerup{
  String type;
  float xpos,ypos;
  boolean inplay;
  boolean used;
  int price;
  powerup(float x, float y, String t, boolean i, boolean u, int p){
    xpos=x;
    ypos=y;
    type=t;
    inplay=i;
    used=u;
    price=p;
  }
  public void update(){
    fill(128,128,128,128);
    rectMode(CENTER);

    if(inplay&&!used){
    rect(xpos,ypos,100,100);
    if(price!=0){
      textSize(50);
      fill(0);
      text(price,xpos,ypos+100);
      
    }
    imageMode(CENTER);
    if(type=="health_canister"){
     image(health,xpos,ypos,200,200);
    }
      if(type=="damage"){
        image(damage,xpos,ypos,100,100);
      }
      if(type=="reach"){
        image(range,xpos,ypos,100,100);
        
      }
      if(type=="movement"){
        
        image(movement,xpos,ypos,100,100);
      }
    if((player1.xpos-xpos)*(player1.xpos-xpos)+(player1.ypos-ypos)*(player1.ypos-ypos)<2500&&player1.money>=price){
      if(type=="health_canister"){
        player1.maxHealth+=50;
        player1.health=player1.maxHealth;
      }
      
      if(type=="damage"){
        
        player1.damage+=10;
        
      }
      if(type=="reach"){
        
        player1.reach+=50;
      }
      if(type=="movement"){
        
        player1.movs+=3;
      }
      used=true;
      player1.money-=price;
    }
    
  }
  }
}
  public void settings() {  size(1920, 1080, P2D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "--present", "--window-color=#666666", "--stop-color=#cccccc", "Main" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
