var goog = goog || {}; // make sure the goog object is created
if(global) {
  goog.global = global; // set goog.global to point to the global QML context property
  global.goog = goog; // make sure goog is defined in the global  context
}
