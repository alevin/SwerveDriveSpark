/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team5507.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLv2Team1ComplexCargo extends CommandGroup {
 
  public AutoLv2Team1ComplexCargo() {
     //going to bay 1 
     addSequential(new DriveForwardDistance(20, 0.5));  
     addSequential(new RotateAngle(90));
     addSequential(new AutoAlign(90));
     addSequential(new CargoOut());
     addSequential(new CargoIn());
 
     //going to loading station 
     addSequential(new DriveForwardDistance(-4.3, 0.5));
     addSequential(new RotateAngle(90));
     addSequential(new DriveForwardDistance(23,0.5));
     addSequential(new AutoAlign(180));
 
     //going to bay2
     addSequential(new DriveForwardDistance(-20, .5)); 
     addSequential(new RotateAngle(90));
     addSequential(new DriveForwardDistance(5.1,0.5));
     addSequential(new AutoAlign(90));
     addSequential(new CargoOut());
     addSequential(new CargoIn());
     
  }
}
