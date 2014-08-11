
var game = new Phaser.Game(800, 600, Phaser.CANVAS, 'gameDiv', { preload: preload, create: create, update: update, render: render });

var arrowSprite;

var directionLabel;

var isAccelerating = false;

var centerX = 400;

var centerY = 300;

var controlsPoller;

var controlsUrl = "../controller";

function angleToDirection(angle)
{
    var direction;
    switch(true)
    {
        case(-120 < angle && angle <= -60):
        {
            direction = "FORWARD";
            break;
        }
        case(-60 < angle && angle <= 0):
        {
            direction = "FORWARD_RIGHT";
            break;
        }
        case(-180 < angle && angle <= -120):
        {
            direction = "FORWARD_LEFT";
            break;
        }
        case(120 > angle && angle >= 60):
        {
            direction = "BACKWARD";
            break;
        }
        case(60 > angle && angle >= 1):
        {
            direction = "BACKWARD_RIGHT";
            break;
        }
        case(180 > angle && angle >= 120):
        {
            direction = "BACKWARD_LEFT";
            break;
        }
        default:
        {
            direction = "unkown: " + angle;
        }
    }

    return direction;
}

function create() 
{
    game.physics.startSystem(Phaser.Physics.ARCADE);
    
    game.stage.backgroundColor = '#0072bc';

    var directionsSprite = game.add.sprite(centerX, centerY, 'directions');
    directionsSprite.anchor.setTo(0.5, 0.5);
    
    arrowSprite = game.add.sprite(centerX, centerY, 'arrow');
    arrowSprite.anchor.setTo(0.5, 0.5);

    arrowSprite.inputEnabled = true;
    
    arrowSprite.events.onInputUp.add(updateLabel);    
    arrowSprite.events.onInputDown.add(updateLabel, this);
    
    arrowSprite.input.useHandCursor = true;    
    
    directionLabel = game.add.text(350, 56, 'Directions', { fontSize: '32px', fill: '#000' });
    
    // read the controls every second
    controlsPoller = setInterval(readControls, 1000);
    
    window.onunload = function()
    {
        clearInterval(controlsPoller);
    };
}

function preload() 
{
    game.load.image('directions', 'assets/sprites/directions.png');
    game.load.image('arrow', 'assets/sprites/arrow.png');
}

function readControls()
{
    if( arrowSprite.input.pointerDown() )
    {
        directionLabel.text = angleToDirection(arrowSprite.angle);
//        directionLabel.text = "is down";
    }
    else
    {
        directionLabel.text = "STOP";
    }
}

function render() 
{
    game.debug.spriteInfo(arrowSprite, 32, 32);
    
    game.debug.spriteInputInfo(arrowSprite, 32, 232);
    game.debug.geom(arrowSprite.input._tempPoint);
}

function update() 
{
    //  This will update the arrowSprite.rotation so that it points to the currently active pointer
    //  On a Desktop that is the mouse, on mobile the most recent finger press.
    arrowSprite.rotation = game.physics.arcade.angleToPointer(arrowSprite);
}

function updateLabel()
{
    isAccelerating = !isAccelerating;

//    directionLabel.text = isAccelerating ? 'Accelerating' : 'Idle';
    
    var params = "message=" + directionLabel.text + "&lname=Ford";
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)      
        {
            var s = xmlhttp.responseText + "<br/>" + document.getElementById("logs").innerHTML;
            document.getElementById("logs").innerHTML = s;
        }
    }
    xmlhttp.open("POST", controlsUrl, true);
    xmlhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
    xmlhttp.send(params);
}
