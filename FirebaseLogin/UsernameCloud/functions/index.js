/* eslint-disable no-var */
const functions = require("firebase-functions");

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//
// The Firebase Admin SDK to access Firestore.
const admin = require("firebase-admin");
admin.initializeApp();

exports.helloWorld = functions.https.onRequest((request, response) => {
  functions.logger.info("Hello logs!", {structuredData: true});
  response.send("My favourite emoji is \u{1F43C}");
});

exports.addUserToFirestore = functions.auth.user().onCreate((user) => {
  // this code runs everytime a new user is created
  // eslint-disable-next-line no-var
  // eslint-disable-next-line semi
  var usersRef = admin.firestore().collection("users")
  return usersRef.doc(user.uid).set({
    displayName: user.displayName,
    emojis: "\u{1F43C}\u{1F33F}\u{1F55C}",
  });
});

