/*Notes
When sending to database, can either use curl command in java (preferred) and convert xml to json
OR send each individual piece to the database
match id
get all pieces of Json
get ether present or just a binary yes or no to depict red/green squares. Should be in order, else we need to order it properly
XML
*/
job: job id
{
  gene:
  Organism
  Accuracy
  Present: (1,0 or yes/no. 1,0 is faster)

}
/*
Need to check
IP issue with web hosting
dynamic table in html/css
in HTML code
*/
<script src="https://www.gstatic.com/firebasejs/3.6.1/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCvLxY6fjWaS2BNaYjsVlFoj-368w8V-1s",
    authDomain: "thegenespot-efb8a.firebaseapp.com",
    databaseURL: "https://thegenespot-efb8a.firebaseio.com",
    storageBucket: "thegenespot-efb8a.appspot.com",
    messagingSenderId: "139442074898"
  };
  firebase.initializeApp(config);
</script>*/
function initialize()
{
  //alert("Hey were connected lmao");

	 var kingdomdrop = document.getElementById("select-kingdom");
	    kingdom=kingdomdrop.options[kingdomdrop.selectedIndex].text;
	 var subtypedrop=document.getElementById("select-subtype");
	 subtype=subtypedrop.options[subtypedrop.selectedIndex].text;
	 var genome_assemdrop=document.getElementById("select-genome-assembiles");
	 genome_assem=genome_assemdrop.options[genome_assemdrop.selectedIndex].text;
  var input=[document.getElementById("job_name").value, document.getElementById("fasta_text_area").value,document.getElementById('word_length').value,document.getElementById('evalue').value,kingdom,subtype,genome_assem];  //repeat for how many
  callGeneSpotService(input);
}

function callGeneSpotService(inputArray)
{

    var location =window.location.href+"analysis/GeneSpot"
    //Results should be the encrypted job id to be able to access the results on the web page.
    var results = $.post(location,{inputArray:inputArray},function(results){
    })
    .done(function(results)
  {
    alert(results);
    //alert("Now Processing, please check back in the results section with your job id in a few moments: "+ uuid);
  })
  .fail(function(results)
  {
    alert("Something went wrong please contact the systems administrator for help");
  })
}


function getResults()
{
    //get jobID from user
    var jobID = document.getElementById('jobId');

    //Reference firebase database
    var db = firebase.database();

    //Attach listener at job node
    var results = firebase.database().ref(jobID);
    results.on('value', function(snapshot) {
      updateResults(jobId, snapshot.val());
    });
}