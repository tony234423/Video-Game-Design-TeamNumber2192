player player1;
obstacle obstacles[][]= new obstacle[2][7];//the jagged arrays that can be used in java do not work here for some reason,so I have to use a dummy variable
PImage img;
button[] buttons=new button[2];
enemy[][] enemies = new enemy[2][4];
boolean paused;
int level;
int levelCap=1;
boolean won;
int frames;
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
  player1= new player(100,250,250,5);
  obstacles[0][0]= new obstacle(0.,0.,200.,50.,false,true,false);
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
  
  enemies[0][0]=new enemy(10,100,500,1000,5,30,"melee",true);
  enemies[0][1]=new enemy(10,100,500,900,5,30,"melee",false);
  enemies[0][2]=new enemy(10,100,600,1000,5,30,"melee",false);
  enemies[0][3]=new enemy(10,100,500,700,5,30,"melee",false);
  enemies[1][0]=new enemy(15,250,width/2,height/2,3,100,"boss",true);
  enemies[1][1]=new enemy(10,100,width/6,height/2,6,30,"melee",false);
  enemies[1][2]=new enemy(10,100,width*1/2,height/2,6,30,"melee",false);
  enemies[1][3]=new enemy(10,100,width*5/6,height/2,6,30,"melee",false);
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
  player1.move();
  player1.update();
  fill(0);
  textSize(20);
  text("Level "+(level+1),width-100,100);

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
    if(enemies[0][0].alive==false&& enemies[0][1].inplay==false){
      enemies[0][1].inplay=true;
      enemies[0][2].inplay=true;
      enemies[0][3].inplay=true;
    
    }
  }
  fill(0);
  text("Time(s):"+round(frames/frameRate),width-100,height-100);
  
  
  
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
}
void mousePressed(){
  player1.attack();
}

    

  
