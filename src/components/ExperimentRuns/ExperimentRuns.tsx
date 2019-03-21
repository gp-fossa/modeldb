import { ColumnApi, GridApi, GridReadyEvent } from 'ag-grid-community';
import 'ag-grid-community/dist/styles/ag-grid.css';
import 'ag-grid-community/dist/styles/ag-theme-material.css';
import { AgGridReact } from 'ag-grid-react';
import * as React from 'react';
import { connect } from 'react-redux';
import { RouteComponentProps } from 'react-router';
import { FilterContextPool } from 'models/FilterContextPool';
import { PropertyType } from 'models/Filters';
import ModelRecord from 'models/ModelRecord';
import routes, { GetRouteParams } from 'routes';
import { IColumnMetaData } from 'store/dashboard-config';
import { fetchExperimentRuns } from 'store/experiment-runs';
import { IApplicationState, IConnectedReduxProps } from 'store/store';
import loader from '../images/loader.gif';
import styles from './ExperimentRuns.module.css';
import { defaultColDefinitions, returnColumnDefs } from './columnDefinitions/Definitions';
import DashboardConfig from './DashboardConfig/DashboardConfig';

let currentProjectID: string;
const locationRegEx = /\/project\/[a-z0-9\-]+\/exp-runs/gim;
FilterContextPool.registerContext({
  getMetadata: () => [{ propertyName: 'Name', type: PropertyType.STRING }, { propertyName: 'Tag', type: PropertyType.STRING }],
  isFilteringSupport: true,
  isValidLocation: (location: string) => {
    return locationRegEx.test(location);
  },
  name: 'ModelRecord',
  onApplyFilters: (filters, dispatch) => {
    dispatch(fetchExperimentRuns(currentProjectID, filters));
  },
  onSearch: (text: string, dispatch) => {
    dispatch(
      fetchExperimentRuns(currentProjectID, [
        {
          invert: false,
          name: 'Name',
          type: PropertyType.STRING,
          value: text
        }
      ])
    );
  }
});

type IUrlProps = GetRouteParams<typeof routes.expirementRuns>;

interface IPropsFromState {
  data?: ModelRecord[] | undefined;
  loading: boolean;
  defaultColDefinitions: any;
  filterState: { [index: string]: {} };
  filtered: boolean;
  columnConfig: Map<string, IColumnMetaData>;
}

interface IOperator {
  '>': any;
  '<': any;
  [key: string]: any;
}

type AllProps = RouteComponentProps<IUrlProps> & IPropsFromState & IConnectedReduxProps;
class ExperimentRuns extends React.Component<AllProps> {
  public gridApi: any;
  public columnApi: any;

  public componentDidMount() {
    currentProjectID = this.props.match.params.projectId;
    this.props.dispatch(fetchExperimentRuns(currentProjectID));
  }

  public callFilterUpdate = () => {
    this.gridApi.onFilterChanged();
  };

  public componentWillReceiveProps(newProps: AllProps) {
    const updatedConfig = this.props.columnConfig;
    if (this.gridApi && updatedConfig !== undefined) {
      setTimeout(this.callFilterUpdate, 100);
      this.gridApi.setColumnDefs(returnColumnDefs(updatedConfig));
      const el = document.getElementsByClassName('ag-center-cols-viewport');
      if (el !== undefined && el[0] !== undefined) {
        el[0].scrollLeft += 200;
      }
    }
  }

  public componentDidUpdate() {
    if (this.props.data !== undefined && this.gridApi !== undefined) {
      this.gridApi.setRowData(this.props.data);
    }
  }
  public render() {
    const { data, loading, columnConfig } = this.props;

    return loading ? (
      <img src={loader} className={styles.loader} />
    ) : data ? (
      <div>
        <DashboardConfig />
        <div className={`ag-theme-material ${styles.aggrid_wrapper}`}>
          <AgGridReact
            pagination={true}
            onGridReady={this.onGridReady}
            animateRows={true}
            getRowHeight={this.gridRowHeight}
            columnDefs={returnColumnDefs(columnConfig)}
            rowData={undefined}
            domLayout="autoHeight"
            defaultColDef={this.props.defaultColDefinitions}
          />
        </div>
      </div>
    ) : (
      ''
    );
  }

  public onGridReady = (event: GridReadyEvent) => {
    this.gridApi = event.api;
    this.columnApi = event.columnApi;
    this.gridApi.setRowData(this.props.data);
  };

  public gridRowHeight = (params: any) => {
    const data = params.node.data;

    if (data.metrics.length > 3 || data.hyperparameters.length > 3) {
      if (data.metrics.length > data.hyperparameters.length) {
        return (data.metric.length - 3) * 5 + 220;
      }
      return data.hyperparameters.length * 5 + 220;
    }
    if (data.tags.length >= 1) {
      return 220;
    }

    return 200;
  };
}

// filterState and filtered should be provided by from IApplicationState -> customFilter
const mapStateToProps = ({ experimentRuns, dashboardConfig }: IApplicationState) => ({
  defaultColDefinitions,
  columnConfig: dashboardConfig.columnConfig,
  data: experimentRuns.data,
  loading: experimentRuns.loading,
  filterState: {},
  filtered: false
});

export default connect(mapStateToProps)(ExperimentRuns);
