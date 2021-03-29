import processing.sound.*;
player player1;
int levelCap=9;
SoundFile fireballSound;
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
PImage background;
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
button[] buttons=new button[2];
enemy[][] enemies = new enemy[levelCap+1][4];
powerup[][] powerups= new powerup[levelCap+1][1];
boolean paused;
int level;//one less than the level number, since this variable starts from 0 at the first level
boolean won;
int frames;

powerup[] powerupshop= new powerup[3];
powerup[] powerupshop2= new powerup[3];
void setup(){
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
  fireballSound= new SoundFile(this,"fire_noise.wav");
  background(220);
  size(1920, 1080, P2D);
  frameRate(60);
  ellipseMode(CENTER);
  rectMode(CORNERS);
  imageMode(CENTER);
  noStroke();
  textAlign(CENTER);
  player1= new player(1000,250,250,5);
  obstacles[0][0]= new obstacle(0.,0.,200.,50.,false,true,false); // visually left top xpos ypos, right bottom xpos ypos, spike, invis,door
  obstacles[0][1]= new obstacle(1000.,500.,1200.,700.,true,false,false);
  obstacles[0][2]= new obstacle(1400.,500.,1600.,700.,false,false,false);
  obstacles[0][3]= new obstacle(1400.,700.,1600.,900.,true,false,false);
  obstacles[0][4]=new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[0][5]=new obstacle(width/2-150,0,width/2-100,100,true,false,false);
  obstacles[0][6]=new obstacle(width/2+100,0,width/2+150,100,true,false,false);
  obstacles[1][0]=new obstacle(width/2+100,height-50,width,height,true,false,false);
  obstacles[1][1]=new obstacle(0,height-50,width/2-100,height,true,false,false);
  obstacles[1][2]=new obstacle(0,0,width/2-100,100,true,false,false);
  obstacles[1][3]=new obstacle(0,0,50,height,true,false,false);
  obstacles[1][4]=new obstacle(width-50,0,width,height,true,false,false);
  obstacles[1][5]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[1][6]=new obstacle(width/2+100,0,width,100,true,false,false);
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
  obstacles[5][2]=new obstacle(0,0,width/2-100,100,false,false,false);
  obstacles[5][3]=new obstacle(0,0,50,height,false,false,false);
  obstacles[5][4]=new obstacle(width-50,0,width,height,false,false,false);
  obstacles[5][5]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  obstacles[5][6]=new obstacle(width/2+100,0,width,100,false,false,false);
  obstacles[6][0]=new obstacle(width/2+100,height-100,width,height,true,false,false);
  obstacles[6][1]=new obstacle(0,height-100,width/2-100,height,true,false,false);
  obstacles[6][2]=new obstacle(0,0,width/2-100,100,true,false,false);
  obstacles[6][3]=new obstacle(0,0,100,height,true,false,false);
  obstacles[6][4]=new obstacle(width-100,0,width,height,true,false,false);
  obstacles[6][5]= new obstacle(width/2-100,0,width/2+100,100,true,true,true);
  obstacles[6][6]=new obstacle(width/2+100,0,width,100,true,false,false);
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
  obstacles[9][0]= new obstacle(0,0,width/2-100,100,false,false,false);
  obstacles[9][1]= new obstacle(width/2+100,0,width,100,false,false,false);
  obstacles[9][2]= new obstacle(width/2+100,height-100,width,height,false,false,false);
  obstacles[9][3]= new obstacle(0,height-100,width/2-100,height,false,false,false);
  obstacles[9][4]=new obstacle(0,0,width/2-100,100,false,false,false);
  obstacles[9][5]=new obstacle(width/2+100,0,width/2+150,100,false,true,false);
  obstacles[9][6]= new obstacle(width/2-100,0,width/2+100,100,false,true,true);
  enemies[0][0]=new enemy(10,100,500,1000,5,30,"melee",true,10);//damage, health, xpos, ypos, movement, size, behavior, inplay, loot
  enemies[0][1]=new enemy(10,100,500,900,5,30,"melee",false,10);
  enemies[0][2]=new enemy(10,100,600,1000,5,30,"melee",false,10);
  enemies[0][3]=new enemy(10,100,500,700,5,30,"melee",false,10);
  enemies[1][0]=new enemy(15,250,width/2,height/2,3,100,"boss1",true,100);
  enemies[1][1]=new enemy(10,100,width/6,height/2,6,30,"melee",false,10);
  enemies[1][2]=new enemy(10,100,width*1/2,height/2,6,30,"melee",false,10);
  enemies[1][3]=new enemy(10,100,width*5/6,height/2,6,30,"melee",false,10);
  enemies[2][0]=new enemy(10,100,1700,1000,5,30,"limit melee",true,25);
  enemies[2][1]=new enemy(10,100,400,500,5,30,"limit melee",true,40);
  enemies[2][2]=new enemy(10,100,500,100,5,30,"limit melee",true,25);
  enemies[2][3]=new enemy(10,0,100,100,6,3,"limit melee",false,15);
  enemies[3][0]=new enemy(10,150,width/2,height/2,6,45,"sword",true,30);
  enemies[3][1]=new enemy(10,150,width/2+100,height/2,5,45,"sword",false,25);
  enemies[3][2]=new enemy(10,150,width/2-100,height/2,5,45,"sword",false,25);
  enemies[3][3]=new enemy(10,0,width/2-100,height/2,5,60,"sword",false,20);
  enemies[4][0]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][1]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][2]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[5][0]=new enemy(15,750,width/2,height/2,5,100,"boss2",true,100);
  enemies[5][1]=new enemy(20,50,width/2-100,height/2,4,30,"melee",false,20);
  enemies[5][2]=new enemy(20,50,width/2,height/2,4,30,"melee",false,20);
  enemies[5][3]=new enemy(20,50,width/2+100,height/2,4,30,"melee",false,20);
  enemies[6][0]=new enemy(10,150,width/2-100,height/2,5,30,"jumping",true,30);
  enemies[6][1]=new enemy(10,150,width/2+100,height/2,4,30,"jumping",true,30);
  enemies[6][2]=new enemy(10,150,width/2,height/2-100,4,30,"jumping",true,30);
  enemies[6][3]=new enemy(10,150,width/2,height/2+100,4,30,"jumping",true,30);
  enemies[7][0]=new enemy(10,250,width/2-200,height/2+200,5,30,"jumping",true,30);
  enemies[7][1]=new enemy(10,250,width/2+200,height/2+200,4,30,"jumping",true,30);
  enemies[7][2]=new enemy(10,250,width/2+200,height/2-200,4,30,"jumping",true,30);
  enemies[7][3]=new enemy(10,250,width/2-200,height/2-200,4,30,"jumping",true,30);
  enemies[8][0]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][1]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][2]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[8][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[9][0]=new enemy(20,500,width/2,height/2,5,100,"boss3",true,20);
  enemies[9][1]=new enemy(15,200,width/2,height/2,5,60,"rider",false,20);
  enemies[9][2]=new enemy(15,300,width/2-100,height/2,5,100,"dragon",false,20);
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
  powerupshop2[1]= new powerup(width/2-200,height/2,"damage",true,false,125);
  powerupshop2[2]= new powerup(width/2+200,height/2,"movement",true,false,150);
  paused=true;
  buttons[0]= new button(width/2-50.,height*3/4-50.,width/2+50.,height*3/4+50.,"Restart?","reset",false);
  buttons[1]=new  button(width/2-50.,height*1/2-50.,width/2+50.,height*1/2+50.,"Start?","start",true);
  level=-1;
  won=false;
  frames=0;
}
void draw(){
  
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
    text("SWORDSMAN GAME",width/2,height/4);
  }
  if(!paused&&level<=levelCap){
    frames+=1;
    imageMode(CORNERS);
  ///image(background,0,0,1080,1920);
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
  fill(0);
  textSize(40);
  text("Level "+(level+1),width-200,200);
  text(player1.money+" coins",width-200,300);
  }

  
 
  
  
  for(int i=0;i<buttons.length;i++){
    buttons[i].update();
    
  }
  if(level==0&&!paused){
    fill(0);
    textSize(30);
    text("Arrow Keys or WASD to Move",400,100);
    text("Click to attack, use cursor to aim, a mouse is recommended",600,300);
    text("Flashing white shows immunity",400,700);
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

void keyReleased(){
  if(keyCode==UP||keyCode==DOWN||key=='s'||key=='S'||key=='W'||key== 'w'){
    player1.ymov=0;
  }
  if(keyCode==LEFT||keyCode==RIGHT||key=='a'||key=='A'||key=='d'||key=='D'){
    player1.xmov=0;
  }
}
void keyPressed(){
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
void mousePressed(){
  player1.attack();
  
}
