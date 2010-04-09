#!/usr/local/bin/thrift --gen java --gen php --gen xsd

include "shared.thrift"

namespace java palinTheorem
namespace php palinTheorem

service PalinTheorem {
   string genQuote()
}