import QtQuick 2.2
import "qml_cljs.js" as CLJS

QtObject {
         Component.onCompleted: example.repl.init_repl(this, 9001)

}
