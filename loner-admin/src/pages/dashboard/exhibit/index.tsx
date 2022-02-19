import IntroduceRow from "@/pages/dashboard/exhibit/components/IntroduceRow";
import {useRequest} from "@@/plugin-request/request";
import {fakeChartData} from "@/pages/dashboard/analysis/service";

const Exhibit = () => {
  const { loading, data } = useRequest(fakeChartData);
  return (
    <div>
      <IntroduceRow loading={loading} visitData={data?.visitData || []} />
    </div>
  );
};

export default Exhibit;
