import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:compressor/compressor.dart';

void main() {
  const MethodChannel channel = MethodChannel('compressor');

  TestWidgetsFlutterBinding.ensureInitialized();

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await Compressor.platformVersion, '42');
  });
}
