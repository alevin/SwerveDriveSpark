
package org.usfirst.frc.team5507.robot;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc.team5507.robot.subsystems.Climber;
import org.usfirst.frc.team5507.robot.subsystems.HatchDelivery;
import org.usfirst.frc.team5507.robot.subsystems.Limelight;
//import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveModule;
import org.usfirst.frc.team5507.robot.subsystems.SwerveDriveSubsystem;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	public static final boolean DEBUG = true;
	
	
	public static SwerveDriveSubsystem swerveDriveSubsystem;
	private Timer timer; 
	public static Climber m_climber;
	public static HatchDelivery m_HatchDelivery;
	public static Limelight m_Limelight;
	public Compressor compressor;
	public AHRS m_ahrs;
	public static Command m_autoCommand;
	SendableChooser<Integer> m_chooser = new SendableChooser<>();
	
	private static OI mOI;
	public static OI getOI() {
		return mOI;
	}

	//Auto system
	private static final int START_DEFAULT = 0;
	private static final int START_RIGHT_2 = 1;
	private static final int START_LEFT_2 = 2;
	private static final int START_RIGHT_1 = 3;
	private static final int START_CENTER_1 = 4;
	private static final int START_LEFT_1 = 5;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		compressor = new Compressor();
		swerveDriveSubsystem = new SwerveDriveSubsystem();
		m_Limelight = new Limelight();
		//m_climber = new Climber();
		m_HatchDelivery = new HatchDelivery();
		timer = new Timer();	
		//Climber.setPID(Robot.m_climber.getPIDControllerArm1(), .2 , 1e-4, 1);
		//Climber.setPID(Robot.m_climber.getPIDControllerArm2(), .2 , 1e-4, 1);
		mOI = new OI(this);
		mOI.registerControls();
		swerveDriveSubsystem.zeroGyro();

		m_chooser.addDefault("Get off hab zone", START_DEFAULT);
		m_chooser.addObject("Starting level 2 right", START_RIGHT_2);
		m_chooser.addObject("Starting level 2 left", START_LEFT_2);
		m_chooser.addObject("Starting level 1 right", START_RIGHT_1);
		m_chooser.addObject("Starting level 1 center", START_CENTER_1);
		m_chooser.addObject("Starting level 1 left", START_LEFT_1);
	}

	@Override
	public void robotPeriodic() {
		SmartDashboard.putNumber("Adjusted Drivetrain Angle", swerveDriveSubsystem.getGyroAngle());
		SmartDashboard.putNumber("Raw Drivetrain Angle", swerveDriveSubsystem.getRawGyroAngle());
		SmartDashboard.putNumber("Drivetrain Rate", swerveDriveSubsystem.getGyroRate());
		SmartDashboard.putNumber("Gyro Update Rate", swerveDriveSubsystem.getNavX().getActualUpdateRate());
		for (int i = 0; i < 4; i++) {
			SmartDashboard.putNumber("Drive Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getDriveMotor().getOutputCurrent());
			SmartDashboard.putNumber("Angle Current Draw " + i, swerveDriveSubsystem.getSwerveModule(i).getAngleMotor().getOutputCurrent());
			//System.out.println("Module " + i  + ": " + swerveDriveSubsystem.getSwerveModule(i).getCurrentAngle());
		}
	//System.out.println("Module 2: " + swerveDriveSubsystem.getSwerveModule(2).getAngleMotor().getOutputCurrent());
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		for (int i = 0; i < 4; i++) {
			swerveDriveSubsystem.getSwerveModule(i).robotDisabledInit();
		}
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 * <p>
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		//compressor.setClosedLoopControl(true);

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		//m_climber.armOneMove(mOI.getController().getLeftYValue());
		
	}

	@Override
	public void testInit() {
		timer.reset();
		timer.start();
	}
	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		swerveDriveSubsystem.holonomicDrive(0.2, 0, 0);
	}

	public SwerveDriveSubsystem getDrivetrain() {
		return swerveDriveSubsystem;
	}
}