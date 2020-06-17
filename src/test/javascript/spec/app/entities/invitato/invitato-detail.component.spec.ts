import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { EoloprjTestModule } from '../../../test.module';
import { InvitatoDetailComponent } from 'app/entities/invitato/invitato-detail.component';
import { Invitato } from 'app/shared/model/invitato.model';

describe('Component Tests', () => {
  describe('Invitato Management Detail Component', () => {
    let comp: InvitatoDetailComponent;
    let fixture: ComponentFixture<InvitatoDetailComponent>;
    const route = ({ data: of({ invitato: new Invitato(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [EoloprjTestModule],
        declarations: [InvitatoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(InvitatoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(InvitatoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load invitato on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.invitato).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
