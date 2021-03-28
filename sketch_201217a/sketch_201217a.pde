player player1;
int levelCap=5;
obstacle obstacles[][]= new obstacle[levelCap+1][7];//the jagged arrays that can be used in java do not work here for some reason,so I have to use dummy variables
PImage img;
button[] buttons=new button[2];
enemy[][] enemies = new enemy[levelCap+1][4];
powerup[][] powerups= new powerup[levelCap+1][1];
boolean paused;
int level;
boolean won;
int frames;
powerup[] powerupshop= new powerup[3];
void setup(){
  img= loadImage("The_Knight_Idle.png");
  background(220);
  fullScreen();//(1080,1920)
  frameRate(60);
  ellipseMode(CENTER);
  rectMode(CORNERS);
  imageMode(CENTER);
  noStroke();
  textAlign(CENTER);
  player1= new player(200,250,250,5);
  obstacles[0][0]= new obstacle(0.,0.,200.,50.,false,true,false); // visually left top xpos ypos, right bottom xpos ypos, spike, invis,door
  obstacles[0][1]= new obstacle(1000.,500.,1200.,700.,true,false,false);
  obstacles[0][2]= new obstacle(1400.,500.,1600.,700.,false,false,false);
  obstacles[0][3]= new obstacle(1400.,700.,1600.,900.,true,false,false);
  obstacles[0][4]=new obstacle(width/2-100,0,width/2+100,25,false,true,true);
  obstacles[0][5]=new obstacle(width/2-150,0,width/2-100,25,true,false,false);
  obstacles[0][6]=new obstacle(width/2+100,0,width/2+150,25,true,false,false);
  obstacles[1][0]=new obstacle(width/2+100,height-50,width,height,true,false,false);
  obstacles[1][1]=new obstacle(0,height-50,width/2-100,height,true,false,false);
  obstacles[1][2]=new obstacle(0,0,width/2-100,50,true,false,false);
  obstacles[1][3]=new obstacle(0,0,50,height,true,false,false);
  obstacles[1][4]=new obstacle(width-50,0,width,height,true,false,false);
  obstacles[1][5]= new obstacle(width/2-100,0,width/2+100,50,false,true,true);
  obstacles[1][6]=new obstacle(width/2+100,0,width,50,true,false,false);
  obstacles[2][0]= new obstacle(0,200,1000,300,true, false, false);
  obstacles[2][1]= new obstacle(0,0,50,200,false,false,true);
  obstacles[2][2]= new obstacle(950,300,1000,900,true,false,false);
  obstacles[2][3]= new obstacle (200,850,950,900,true,false,false);
  obstacles[2][4]= new obstacle(200,400,250,850,true,false,false);
  obstacles[2][5]= new obstacle(1000,200,1700,250,true,false,false);
  obstacles[2][6]= new obstacle(1300,250,1350,800,true,false,false);
  obstacles[3][0]= new obstacle(1400,200,1600,800,false,false,false);
  obstacles[3][1]= new obstacle(200,200,400,800,false,false,false);
  obstacles[3][2]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][3]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][4]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][5]= new obstacle(0,0,0,0,false,true,false);
  obstacles[3][6]= new obstacle(width/2-50,0,width/2+50,20,false,true,true);
  obstacles[4][0]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][1]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][2]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][3]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][4]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][5]= new obstacle(0,0,0,0,false,true,false);
  obstacles[4][6]= new obstacle(width/2-50,0,width/2+50,20,false,false,true);
  obstacles[5][0]=new obstacle(width/2+100,height-50,width,height,false,false,false);
  obstacles[5][1]=new obstacle(0,height-50,width/2-100,height,false,false,false);
  obstacles[5][2]=new obstacle(0,0,width/2-100,50,false,false,false);
  obstacles[5][3]=new obstacle(0,0,50,height,false,false,false);
  obstacles[5][4]=new obstacle(width-50,0,width,height,false,false,false);
  obstacles[5][5]= new obstacle(width/2-100,0,width/2+100,50,false,true,true);
  obstacles[5][6]=new obstacle(width/2+100,0,width,50,false,false,false);
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
  enemies[3][0]=new enemy(10,150,width/2,height/2,6,30,"sword",true,30);
  enemies[3][1]=new enemy(10,150,width/2+100,height/2,5,30,"sword",false,25);
  enemies[3][2]=new enemy(10,150,width/2-100,height/2,5,30,"sword",false,25);
  enemies[3][3]=new enemy(10,0,width/2-100,height/2,5,30,"sword",false,20);
  enemies[4][0]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][1]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][2]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[4][3]=new enemy(10,0,width/2-100,height/2,5,30,"",false,20);
  enemies[5][0]=new enemy(15,500,width/2-100,height/2,5,30,"boss2",true,20);
  enemies[5][1]=new enemy(20,50,width/2-100,height/2,4,30,"melee",false,20);
  enemies[5][2]=new enemy(20,50,width/2,height/2,4,30,"melee",false,20);
  enemies[5][3]=new enemy(20,50,width/2+100,height/2,4,30,"melee",false,20);
  //position,type,inplay,used
  powerups[0][0]= new powerup(0,0,"",false,true,0);//level one doesnt get a powerup
  powerups[1][0]= new powerup(width/2,height/2,"health_canister",false,false,0);
  powerups[2][0]= new powerup(500,600,"reach",true,false,0);
  powerups[3][0]= new powerup(width/2,height/2,"damage",false,false,0);
  powerups[4][0]= new powerup(0,0,"",false,true,0);
  powerups[5][0]= new powerup(0,0,"",false,true,0);
  //level five will be a shop before the final boss fight
  powerupshop[0]= new powerup(width/2,height/2,"health_canister",true,false,175);
  powerupshop[1]= new powerup(width/2-200,height/2,"damage",true,false,125);
  powerupshop[2]= new powerup(width/2+200,height/2,"movement",true,false,150);
  
  paused=true;
  buttons[0]= new button(width/2-50.,height*3/4-50.,width/2+50.,height*3/4+50.,"Restart?","reset",false);
  buttons[1]=new  button(width/2-50.,height*1/2-50.,width/2+50.,height*1/2+50.,"Start?","start",true);
  level=-1;
  won=false;
  frames=0;
}
void draw(){
  
  
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
    text("SWORD MAN GAME",width/2,height/4);
  }
  if(!paused&&level<=levelCap){
    frames+=1;
  background(220);
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
    
  }
  player1.move();
  player1.update();
  fill(0);
  textSize(40);
  text("Level "+(level+1),width-200,100);
  text(player1.money+" coins",width-200,200);
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
    text("I am not spiked",1500,800);
    text("Door will spawn here", width/2,50);
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
 
  ///fill(0);
  ///text("Time(s):"+frames/frameRate,width-100,height-100);
  ///dont particularly want to include this because it's screwy and also there's too much sidetext
  
  
  
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
    

  
