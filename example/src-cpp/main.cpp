#include <QGuiApplication>
#include <QQmlApplicationEngine>
#include <QQmlContext>

int main(int argc, char *argv[])
{
    QGuiApplication app(argc, argv);

    QQmlApplicationEngine engine;
    QObject* global = new QObject();
    engine.rootContext()->setContextObject(global);
    engine.rootContext()->setContextProperty("global", global);
    engine.load(QUrl(QStringLiteral("qrc:///main.qml")));

    return app.exec();
}
