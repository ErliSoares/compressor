#import "CompressorPlugin.h"
#if __has_include(<compressor/compressor-Swift.h>)
#import <compressor/compressor-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "compressor-Swift.h"
#endif

@implementation CompressorPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftCompressorPlugin registerWithRegistrar:registrar];
}
@end
