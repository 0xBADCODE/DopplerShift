//Dopper Shift 1.0

import java.util.Scanner;
import java.math.*;

class DopplerShift {
	public static void main(String[] args) {

	final 	double 	m_earth = 5.97219e24,
					const_c = 2.99792458e8,
					const_G = 6.667384e-11,
					radius_earth = 6371e3;



			double	radius_sat = 0,
					period_T = 0, //user
					rest_freq = 0, //user
					d_freq = 0,
					velocity_sat = 0,
					velocity_receiver=0,
					d_velocity = 0;

	//enter freq
	Scanner scan = new Scanner(System.in);

	System.out.print("\nGPS L1 signal = 1575.42MHz\nISS = 145.8MHz\n\nPlease enter frequency (MHz): ");//choose freq
		rest_freq = scan.nextDouble();


	//enter period of sat
			System.out.print("\nGeostationary orbit = 1440 minutes\nGPS orbit = 720 minutes (12 hours)\nISS orbit = 92 minutes");
			System.out.print("\n\nPlease enter period of satellite orbit (minutes): ");//choose period_T
			period_T = scan.nextDouble();


	//do mathy bit
	period_T = period_T*60.0; //minutes to seconds

	radius_sat = Math.cbrt(const_G*m_earth*Math.pow(period_T,2)/(Math.pow(2*Math.PI,2)));//calc radius_sat
	double sat_altitude = radius_sat-radius_earth;

	System.out.print("\nSatellite avg. altitude: " + Math.round(sat_altitude)/1e3 + "km (" + Math.round(0.621371192*sat_altitude)/1e3 + " miles)");


	velocity_sat = 2*Math.PI*radius_sat/period_T; //calc velocity

	System.out.print("\nSatellite velocity: " + Math.round(velocity_sat)/1e3 + "km/s (" + Math.round(3600*0.621371192*velocity_sat)/1e3 + " mph)");


	velocity_receiver = 2*Math.PI*radius_earth/86400.0;//calc velocity

	System.out.print("\nReceiver station velocity: " + Math.round(velocity_receiver)/1e3 + "km/s (" + Math.round(3600*0.621371192*velocity_receiver)/1e3 + " mph)");


	d_velocity = 2*Math.PI*(radius_sat/period_T + radius_earth/86400.0); //calc delta_velocity

	d_freq = 1e3*rest_freq*d_velocity/const_c; //MHz to kHz

	BigDecimal bd = new BigDecimal(d_freq);
	bd = bd.round(new MathContext(3));

	System.out.print("\nCarrier frequency variance: (+/-)" + bd + "kHz");
	
	//TODO

	double thi = 2*Math.PI/period_T;//angle in radians

	System.out.print("\n\nangle: " + thi + " rad/s");

	//relativity bit //TODO
	double  gamma = 1.0/Math.sqrt(1.0-(Math.pow(d_velocity/const_c,2)));

	//test data output
	System.out.print("\n\nLorentz fuction (GAMMA) = " + gamma);
	System.out.print("\nSpacetime contraction factor = " + 1/gamma + "\n");

	}
}
