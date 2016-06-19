#!/usr/bin/php
<!DOCTYPE html>
<html>
  <head>
    <link href='https://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>
    <style type="text/css">
      body {
        color: rgba(0,0,0,0.87);
        font-family: 'Roboto', sans-serif;
        font-weight: 400;
        font-size: 13pt;
        background-color: #fafafa;
      }
      table tbody tr td.passed {
        background-color: #43a047;
      }
      table tbody tr td.failed {
        background-color: #e53935;
      }
      table tbody tr td.missing {
        background-color: #757575;
      }
      table tr:hover td.passed {
        background-color: #2e7d32;
      }
      table tr:hover td.failed {
        background-color: #c62828;
      }
      table tr:hover td.missing {
        background-color: #424242;
      }
      table tr:hover td {
        background-color: #e0e0e0;
      }
      a {
        color: #3f51b5;
      }
      p.small {
        font-size: 10pt;
      }
    </style>
  </head>
  <body>
<?php
function count_frequency($needle, $reports) {
  $count = 0;
  foreach($reports as $report) {
    foreach($report as $key => $value) {
      if ($key === $needle && $value == "PASSED") {
        $count++;
      }
    }
  }
  return $count;
}
function comp_header($a, $b) {
  $reports = $GLOBALS["reports"];
  $count_a = count_frequency($a, $reports);
  $count_b = count_frequency($b, $reports);
  if ($count_a == $count_b) {
    return 0;
  }
  return ($count_a > $count_b) ? -1 : 1;
}
function comp_report($a, $b) {
  $count_a = calc_score($a);
  $count_b = calc_score($b);
  if ($count_a == $count_b) {
    return 0;
  }
  return ($count_a > $count_b) ? -1 : 1;
}
function calc_score($report) {
  $headers = $GLOBALS["headers"];
  $score = 0;
  $i = 1;
  foreach($headers as $key) {
    if (array_key_exists($key,$report) && 'PASSED' === $report[$key]) {
      $score += pow(2,$i);
    }
    $i++;
  }
  return $score;
}
$reports = json_decode(file_get_contents('complete.json'),true);
$headers = array_keys(reset($reports));
if (count($argv) >= 2 && $argv[1] === 'ranked') {
  usort($headers, "comp_header");
  uasort($reports, "comp_report");
}
?>
<table>
  <thead>
    <tr>
      <th></th>
      <?php
        foreach($headers as &$head) {
          echo "<th>".htmlentities($head)."</th>";
        }
      ?>
    </tr>
  </thead>
  <tbody>
    <?php
    foreach($reports as $server => $report) {
      echo '<tr>';
      echo '<td>'.htmlentities($server).'</td>';
      foreach($headers as $c) {
        if (!array_key_exists($c, $report)) {
          $class = 'missing';
        } else if ('PASSED' === $report[$c]) {
          $class = 'passed';
        } else {
          $class = 'failed';
        }
        echo '<td class="'.$class.'"></td>';
      }
      echo '</tr>';
    }
    ?>
  </tbody>
</table>
<p class="small">Copyright 2016 <a href="https://gultsch.de">Daniel Gultsch</a> &middot; Data gathered with <a href="https://github.com/iNPUTmice/ComplianceTester">XMPP Compliance Tester</a> &middot; Last update <?= date("Y-m-d")?> (actual data might be older)</p>
</body>
</html>
