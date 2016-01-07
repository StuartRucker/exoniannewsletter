import java.util.ArrayList;

import com.firebase.client.*;


public class firebaseRead {
	public static boolean done = false;
	public static void main(String [] args){
		ArrayList<String> emails = new ArrayList<String>();
		Firebase ref = new Firebase("https://emailexonian.firebaseio.com/");
			ref.addListenerForSingleValueEvent(new ValueEventListener() {
			    @Override
			    public void onDataChange(DataSnapshot snapshot) {
			        for (DataSnapshot child : snapshot.getChildren()) {
			           String em = child.getValue().toString();
			           if(isValidEmailAddress(em)){
			        	   emails.add(em);
			           }
			        }
			        System.out.println("Sending " + emails);
			        EmailSender.sendEmail(emails);
			        done = true;
			    }

				@Override
				public void onCancelled(FirebaseError arg0) {
					// TODO Auto-generated method stub
					
				}
			});
			
			while(!done);
	}
	
	public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
	}

}
